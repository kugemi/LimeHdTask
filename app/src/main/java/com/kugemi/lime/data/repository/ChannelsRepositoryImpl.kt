package com.kugemi.lime.data.repository

import androidx.lifecycle.LiveData
import com.kugemi.lime.data.repository.dataSource.ChannelsRemoteDataSource
import com.kugemi.lime.domain.model.Channels
import com.kugemi.lime.domain.repository.ChannelsRepository

class ChannelsRepositoryImpl(
    private val channelsRemoteDataSource: ChannelsRemoteDataSource
) : ChannelsRepository {
    override suspend fun getChannels() = channelsRemoteDataSource.getChannels()
}