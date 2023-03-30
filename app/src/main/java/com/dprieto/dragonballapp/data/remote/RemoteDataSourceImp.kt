package com.dprieto.dragonballapp.data.remote

import com.dprieto.dragonballapp.data.remote.request.HerosRequest
import com.dprieto.dragonballapp.data.remote.response.HeroResponse
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

    override suspend fun getLocations(id: String): Result<List<HeroResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun setFavorite() {
        TODO("Not yet implemented")
    }

}