package com.kugemi.lime.data.repository.dataSource

import androidx.lifecycle.LiveData
import com.kugemi.lime.domain.model.FavoriteChannel

interface FavoritesLocalDataSource {
    fun getFavorites(): LiveData<List<FavoriteChannel>>

    suspend fun addFavoriteChannel(channel: FavoriteChannel)

    suspend fun removeFavoriteChannel(channel: String)
}