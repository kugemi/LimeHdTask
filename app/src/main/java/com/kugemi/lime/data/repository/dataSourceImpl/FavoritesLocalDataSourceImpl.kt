package com.kugemi.lime.data.repository.dataSourceImpl

import androidx.lifecycle.LiveData
import com.kugemi.lime.data.database.FavoritesDao
import com.kugemi.lime.data.repository.dataSource.FavoritesLocalDataSource
import com.kugemi.lime.domain.model.FavoriteChannel

class FavoritesLocalDataSourceImpl(private val favoritesDao: FavoritesDao) : FavoritesLocalDataSource {
    override fun getFavorites(): LiveData<List<FavoriteChannel>> = favoritesDao.getFavoritesChannels()

    override suspend fun addFavoriteChannel(channel: FavoriteChannel) = favoritesDao.addFavoriteChannel(channel)

    override suspend fun removeFavoriteChannel(channel: String) = favoritesDao.removeFavoriteChannel(channel)
}