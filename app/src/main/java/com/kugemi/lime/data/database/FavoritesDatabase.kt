package com.kugemi.lime.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kugemi.lime.domain.model.FavoriteChannel

@Database(entities = [FavoriteChannel::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun Dao(): FavoritesDao
}