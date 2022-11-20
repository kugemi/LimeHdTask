package com.kugemi.lime.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kugemi.lime.domain.model.FavoriteChannel

@Dao
abstract class FavoritesDao {
    @Query("SELECT * FROM FAVORITES")
    abstract fun getFavoritesChannels(): LiveData<List<FavoriteChannel>>

    @Insert
    abstract fun addFavoriteChannel(channel: FavoriteChannel)

    @Query("DELETE FROM FAVORITES WHERE name = :channel")
    abstract fun removeFavoriteChannel(channel: String)
}