package com.dprieto.dragonballapp.data

import com.dprieto.dragonballapp.ui.herolist.HeroListState

interface Repository {

    suspend fun doLogin(): String
    suspend fun getHeros(name: String = ""): HeroListState

}