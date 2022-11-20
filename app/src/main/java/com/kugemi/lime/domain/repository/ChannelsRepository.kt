package com.kugemi.lime.domain.repository

import androidx.lifecycle.LiveData
import com.kugemi.lime.domain.model.Channels
import com.kugemi.lime.domain.model.FavoriteChannel

interface ChannelsRepository {
    suspend fun getChannels(): Channels

    fun getFavoriteChannels(): LiveData<List<FavoriteChannel>>

    suspend fun addFavoriteChannel(channel: FavoriteChannel)

    suspend fun removeFavoriteChannel(channel: String)
}