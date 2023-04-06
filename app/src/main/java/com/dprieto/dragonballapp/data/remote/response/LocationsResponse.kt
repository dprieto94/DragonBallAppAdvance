package com.dprieto.dragonballapp.data.remote.response

import com.squareup.moshi.Json
import java.util.*

data class LocationsResponse(
    @Json(name = "id") val id: String,
    @Json(name = "dateShow") val dateShow: String,
    @Json(name = "latitud") val latitud: String,
    @Json(name = "longitud") val longitud: String
)
