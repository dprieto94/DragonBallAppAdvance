package com.dprieto.dragonballapp.data

import com.dprieto.dragonballapp.ui.detail.HeroDetailState
import com.dprieto.dragonballapp.ui.herolist.HeroListState

interface Repository {

    suspend fun doLogin(): String
    suspend fun getHeros(): HeroListState
    suspend fun getHeroDetail(name: String): HeroDetailState
    suspend fun getLocations(id: String): HeroDetailState
    suspend fun setFavorite(id: String, name: String): HeroDetailState

}