package com.dprieto.dragonballapp.data.remote

import com.dprieto.dragonballapp.data.remote.response.HeroResponse
import com.dprieto.dragonballapp.data.remote.response.LocationsResponse

interface RemoteDataSource {

    suspend fun doLogin(): Result<String>
    suspend fun getHeros(): Result<List<HeroResponse>>
    suspend fun getHeroDetail(name: String): Result<HeroResponse?>
    suspend fun getLocations(id: String): Result<List<LocationsResponse>>
    suspend fun setFavorite(id: String): Result<Unit>

}