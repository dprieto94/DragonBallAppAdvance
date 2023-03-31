package com.dprieto.dragonballapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "heros")
data class HeroRoomModel (
    @PrimaryKey @Json(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "description") val descripcion: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean
)