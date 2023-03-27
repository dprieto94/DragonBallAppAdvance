package com.dprieto.dragonballapp.data.remote

import retrofit2.http.GET
import retrofit2.http.POST

interface DragonBallApi {

    @POST("api/auth/login")
    suspend fun doLogin(): String

}