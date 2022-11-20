package com.kugemi.lime.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FAVORITES")
class FavoriteChannel {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String = ""
}