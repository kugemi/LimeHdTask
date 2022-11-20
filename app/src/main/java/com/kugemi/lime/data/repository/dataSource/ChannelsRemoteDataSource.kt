package com.kugemi.lime.data.repository.dataSource

import com.kugemi.lime.domain.model.Channels

interface ChannelsRemoteDataSource {
    suspend fun getChannels(): Channels
}