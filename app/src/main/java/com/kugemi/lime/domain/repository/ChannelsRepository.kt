package com.kugemi.lime.domain.repository

import com.kugemi.lime.domain.model.Channels

interface ChannelsRepository {
    suspend fun getChannels(): Channels
}