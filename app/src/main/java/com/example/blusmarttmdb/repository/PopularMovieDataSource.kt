package com.example.blusmarttmdb.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.blusmarttmdb.api.TMDBApi
import com.example.blusmarttmdb.models.PopularMovie
import com.example.blusmarttmdb.utils.INITIAL_PAGE_INDEX
import com.example.blusmarttmdb.utils.LANGUAGE
import com.example.blusmarttmdb.utils.PER_PAGE_ITEMS

class PopularMovieDataSource(
    private val networkService: TMDBApi
) : PagingSource<Int, PopularMovie>() {

    override fun getRefreshKey(state: PagingState<Int, PopularMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovie> {
        val pageIndex = params.key ?: INITIAL_PAGE_INDEX

        return try {
            val response = networkService.getMovieList(
                language = LANGUAGE,
                page = pageIndex
            )


            val movies = response.popularMovies

            val nextKey =
                if (movies.isEmpty()) {
                    null
                } else {
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / PER_PAGE_ITEMS)
                }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == INITIAL_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }

    }
}