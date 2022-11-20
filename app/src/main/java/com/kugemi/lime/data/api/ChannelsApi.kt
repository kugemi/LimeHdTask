package com.kugemi.lime.data.api

import com.kugemi.lime.domain.model.Channels
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ChannelsApi {
    @GET("channels.json")
    fun getChannels(): Deferred<Channels>
}