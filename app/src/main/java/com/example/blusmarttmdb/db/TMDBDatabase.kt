package com.example.blusmarttmdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.blusmarttmdb.models.PopularMovie


@Database(entities = [PopularMovie::class], version = 1)
abstract class TMDBDatabase: RoomDatabase() {
    abstract  fun popularMovieDao(): PopularMovieDao

    companion object {



        @Volatile
        private var INSTANCE: TMDBDatabase? = null
        fun getDatabase(context: Context): TMDBDatabase {
            if (INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        TMDBDatabase::class.java,
                        "tmdbDB")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
