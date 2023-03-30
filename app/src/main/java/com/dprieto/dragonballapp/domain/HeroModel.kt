package com.dprieto.dragonballapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroModel(
    val id: String,
    val name: String,
    val photo: String,
    val favorite: Boolean
) : Parcelable
