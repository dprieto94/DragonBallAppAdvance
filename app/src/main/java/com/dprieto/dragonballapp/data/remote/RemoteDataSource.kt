package com.dprieto.dragonballapp.data.remote

import com.dprieto.dragonballapp.data.remote.response.HeroResponse

interface RemoteDataSource {

    suspend fun  doLogin(): String
    suspend fun getHeros(name: String = ""): Result<List<HeroResponse>>

}