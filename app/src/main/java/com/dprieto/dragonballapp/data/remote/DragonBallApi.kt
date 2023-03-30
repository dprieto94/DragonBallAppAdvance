package com.dprieto.dragonballapp.data.remote

import android.location.LocationRequest
import com.dprieto.dragonballapp.data.remote.request.FavoriteRequest
import com.dprieto.dragonballapp.data.remote.request.HerosRequest
import com.dprieto.dragonballapp.data.remote.response.HeroResponse
import com.dprieto.dragonballapp.data.remote.response.LocationsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DragonBallApi {

    @POST("api/auth/login")
    suspend fun doLogin(): String

    @POST("api/heros/all")
    suspend fun getHeros(@Body herosRequest: HerosRequest): List<HeroResponse>

    @POST("/api/data/herolike")
    suspend fun favorite(@Body favoriteRequest: FavoriteRequest)

    @POST("api/heros/all")
    suspend fun getHeroDetail(@Body herosRequest: HerosRequest): List<HeroResponse>

    @POST("/api/heros/locations")
    suspend fun getHeroLocations(@Body locationRequest: LocationRequest) : List<LocationsResponse>

}