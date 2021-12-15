package com.example.blusmarttmdb.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.blusmarttmdb.R
import com.example.blusmarttmdb.databinding.HomeItemViewBinding
import com.example.blusmarttmdb.models.PopularMovie


class PopularMovieAdapter :
    PagingDataAdapter<PopularMovie, PopularMovieAdapter.PhotoViewHolder>(PhotoDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding: HomeItemViewBinding = HomeItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    inner class PhotoViewHolder(private val binding: HomeItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PopularMovie) {
            binding.popularMovie = item
        }
    }
}

class PhotoDiffCallBack : DiffUtil.ItemCallback<PopularMovie>() {
    override fun areItemsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
        return oldItem == newItem
    }
}