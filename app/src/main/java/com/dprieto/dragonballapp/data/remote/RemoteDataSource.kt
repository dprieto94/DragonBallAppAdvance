package com.dprieto.dragonballapp.data.remote

import com.dprieto.dragonballapp.data.remote.response.HeroResponse

interface RemoteDataSource {

    suspend fun doLogin(): String
    suspend fun getHeros(): Result<List<HeroResponse>>
    suspend fun getHeroDetail(name: String): Result<HeroResponse?>
    suspend fun getLocations(id: String): Result<List<HeroResponse>>
    suspend fun setFavorite()

}