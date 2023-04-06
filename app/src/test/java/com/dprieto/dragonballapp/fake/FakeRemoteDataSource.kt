package com.dprieto.dragonballapp.fake

import com.dprieto.dragonballapp.data.remote.RemoteDataSource
import com.dprieto.dragonballapp.data.remote.response.HeroResponse
import com.dprieto.dragonballapp.data.remote.response.LocationsResponse
import com.dprieto.dragonballapp.utils.generateHerosRemote
import com.dprieto.dragonballapp.utils.generateLocations
import com.dprieto.dragonballapp.utils.generateToken
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class FakeRemoteDataSource(private val expectedStatus: String): RemoteDataSource {


    override suspend fun doLogin(): Result<String> {
        return when(expectedStatus){
            "SUCCESS" -> Result.success(generateToken())
            "NETWORK_ERROR" -> Result.failure(HttpException(Response.success(204, ResponseBody)))
            "ERROR" -> Result.failure(NullPointerException("Error"))
            else -> {Result.failure(Exception())}
        }
    }

    override suspend fun getHeros(): Result<List<HeroResponse>> {
        return when(expectedStatus){
            "SUCCESS" -> Result.success(generateHerosRemote())
            "NETWORK_ERROR" -> Result.failure(HttpException(Response.success(204, ResponseBody)))
            "ERROR" -> Result.failure(NullPointerException("Error"))
            else -> {Result.failure(Exception())}
        }
    }

    override suspend fun getHeroDetail(name: String): Result<HeroResponse?> {
        return when(expectedStatus){
            "SUCCESS" -> Result.success(generateHerosRemote().first())
            "NETWORK_ERROR" -> Result.failure(HttpException(Response.success(204, ResponseBody)))
            "SUCCESS_NULL" -> Result.success(null)
            "ERROR" -> Result.failure(NullPointerException("Error"))
            else -> {Result.failure(Exception())}
        }
    }

    override suspend fun getLocations(id: String): Result<List<LocationsResponse>> {
        return when(expectedStatus){
            "SUCCESS" -> Result.success(generateLocations())
            "NETWORK_ERROR" -> Result.failure(HttpException(Response.success(204, ResponseBody)))
            "ERROR" -> Result.failure(NullPointerException("Error"))
            else -> {Result.failure(Exception())}
        }
    }

    override suspend fun setFavorite(id: String): Result<Unit> {
        return when(expectedStatus){
            "SUCCESS" -> Result.success(Unit)
            "NETWORK_ERROR" -> Result.failure(HttpException(Response.success(204, ResponseBody)))
            "ERROR" -> Result.failure(NullPointerException("Error"))
            else -> {Result.failure(Exception())}
        }
    }
}