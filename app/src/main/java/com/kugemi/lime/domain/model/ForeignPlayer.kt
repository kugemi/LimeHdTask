package com.kugemi.lime.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ForeignPlayer(
    @SerializedName("sdk")
    val sdk: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("valid_from")
    val valid_from: Int
) : Serializable