package com.dprieto.dragonballapp.data.remote

import com.dprieto.dragonballapp.data.remote.request.FavoriteRequest
import com.dprieto.dragonballapp.data.remote.request.HerosRequest
import com.dprieto.dragonballapp.data.remote.request.LocationsRequest
import com.dprieto.dragonballapp.data.remote.response.HeroResponse
import com.dprieto.dragonballapp.data.remote.response.LocationsResponse
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val api: DragonBallApi): RemoteDataSource {

    override suspend fun  doLogin(): String{
        return api.doLogin()
    }

    override suspend fun getHeros(): Result<List<HeroResponse>> {
        return runCatching { api.getHeros(HerosRequest()) }
    }

    override suspend fun getHeroDetail(name: String): Result<HeroResponse?> {
        return runCatching { api.getHeroDetail(HerosRequest(name = name)).firstOrNull() }
    }

    override suspend fun getLocations(id: String): Result<List<LocationsResponse>> {
        return runCatching { api.getHeroLocations(LocationsRequest(id = id)) }
    }

    override suspend fun setFavorite(id: String): Result<Unit> {
        return runCatching { api.favorite(FavoriteRequest(id)) }
    }

}