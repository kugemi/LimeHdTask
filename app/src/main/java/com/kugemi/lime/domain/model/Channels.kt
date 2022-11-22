package com.kugemi.lime.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Channels(
    @SerializedName("channels")
    val channels: List<Channel>,

    @SerializedName("cacheKey")
    val ckey: String,

    @SerializedName("valid")
    val valid: Int
) : Serializable