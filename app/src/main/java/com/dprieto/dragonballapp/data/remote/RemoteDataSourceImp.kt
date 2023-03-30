package com.dprieto.dragonballapp.data.remote

import com.dprieto.dragonballapp.data.remote.request.HerosRequest
import com.dprieto.dragonballapp.data.remote.response.HeroResponse
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val api: DragonBallApi): RemoteDataSource {

    override suspend fun  doLogin(): String{
        return api.doLogin()
    }

    override suspend fun getHeros(name: String): Result<List<HeroResponse>> {
        return runCatching { api.getHeros(HerosRequest(name)) }
    }

}