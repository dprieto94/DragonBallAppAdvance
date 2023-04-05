package com.dprieto.dragonballapp.data

import com.dprieto.dragonballapp.ui.detail.HeroDetailLocationsState
import com.dprieto.dragonballapp.ui.detail.HeroDetailState
import com.dprieto.dragonballapp.ui.herolist.HeroListState
import com.dprieto.dragonballapp.ui.login.LoginState

interface Repository {

    suspend fun doLogin(): LoginState
    suspend fun getHeros(): HeroListState
    suspend fun getHeroDetail(name: String): HeroDetailState
    suspend fun getLocations(id: String): HeroDetailLocationsState
    suspend fun setFavorite(id: String, name: String): HeroDetailState
    fun saveParam(id: String, value: String)
    fun getParam(id: String): String
}