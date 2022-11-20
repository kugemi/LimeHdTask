package com.kugemi.lime.data.repository.dataSourceImpl

import com.kugemi.lime.data.api.ChannelsApi
import com.kugemi.lime.data.repository.dataSource.ChannelsRemoteDataSource
import com.kugemi.lime.domain.model.Channels
import retrofit2.HttpException

class ChannelsRemoteDataSourceImpl(private val channelsApi: ChannelsApi)
    : ChannelsRemoteDataSource {
    override suspend fun getChannels(): Channels {
        return try {
            val serverResult = channelsApi.getChannels().await()
            Channels(
                channels = serverResult.channels,
                ckey = serverResult.ckey,
                valid = serverResult.valid
            )
        } catch (exception : HttpException) {
            Channels(
                channels = emptyList(),
                ckey = exception.message(),
                valid = 0
            )
        }
    }
}