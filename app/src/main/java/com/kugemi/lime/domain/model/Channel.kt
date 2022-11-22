package com.kugemi.lime.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Channel(
    @SerializedName("address")
    val address: String,

    @SerializedName("cdn")
    val cdn: String,

    @SerializedName("current")
    val current: Current?,

    @SerializedName("drm_status")
    val drm_status: Int,

    @SerializedName("epg_id")
    val epg_id: Int,

    @SerializedName("foreign_player")
    val foreign_player: ForeignPlayer,

    @SerializedName("foreign_player_key")
    val foreign_player_key: Boolean,

    @SerializedName("hasEpg")
    val hasEpg: Boolean,

    @SerializedName("id")
    val id: Int,

    @SerializedName("image")
    val image: String,

    @SerializedName("is_federal")
    val is_federal: Boolean,

    @SerializedName("is_foreign")
    val is_foreign: Boolean,

    @SerializedName("name_en")
    val name_en: String,

    @SerializedName("name_ru")
    val name_ru: String,

    @SerializedName("number")
    val number: Int,

    @SerializedName("owner")
    val owner: String,

    @SerializedName("region_code")
    val region_code: Int,

    @SerializedName("tz")
    val tz: Int,

    @SerializedName("url")
    val url: String,

    @SerializedName("url_sound")
    val url_sound: String,

    @SerializedName("vitrina_events_url")
    val vitrina_events_url: String
) : Serializable