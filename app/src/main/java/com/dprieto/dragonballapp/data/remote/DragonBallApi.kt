package com.dprieto.dragonballapp.data.remote

import com.dprieto.dragonballapp.data.remote.request.HerosRequest
import com.dprieto.dragonballapp.data.remote.response.HeroResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DragonBallApi {

    @POST("api/auth/login")
    suspend fun doLogin(): String

    @POST("api/heros/all")
    suspend fun getHeros(@Body herosRequest: HerosRequest): List<HeroResponse>

}