package com.kugemi.lime.data.repository

import androidx.lifecycle.LiveData
import com.kugemi.lime.data.repository.dataSource.ChannelsRemoteDataSource
import com.kugemi.lime.data.repository.dataSource.FavoritesLocalDataSource
import com.kugemi.lime.domain.model.Channels
import com.kugemi.lime.domain.model.FavoriteChannel
import com.kugemi.lime.domain.repository.ChannelsRepository

class ChannelsRepositoryImpl(
    private val channelsRemoteDataSource: ChannelsRemoteDataSource,
    private val favoritesLocalDataSource: FavoritesLocalDataSource
) : ChannelsRepository {
    override suspend fun getChannels() = channelsRemoteDataSource.getChannels()

    override fun getFavoriteChannels(): LiveData<List<FavoriteChannel>> = favoritesLocalDataSource.getFavorites()

    override suspend fun addFavoriteChannel(channel: FavoriteChannel) = favoritesLocalDataSource.addFavoriteChannel(channel)

    override suspend fun removeFavoriteChannel(channel: String) = favoritesLocalDataSource.removeFavoriteChannel(channel)
}