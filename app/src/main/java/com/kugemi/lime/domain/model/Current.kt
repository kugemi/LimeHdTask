package com.kugemi.lime.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Current(
    @SerializedName("cdnvideo")
    val cdnvideo: Int,

    @SerializedName("desc")
    val desc: String,

    @SerializedName("rating")
    val rating: Int,

    @SerializedName("timestart")
    val timestart: Int,

    @SerializedName("timestop")
    val timestop: Int,

    @SerializedName("title")
    val title: String
) : Serializable