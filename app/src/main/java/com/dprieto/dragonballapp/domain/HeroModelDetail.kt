package com.dprieto.dragonballapp.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroModelDetail(
    val id: String,
    val name: String,
    val photo: String,
    val description: String,
    val favorite: Boolean
) : Parcelable
