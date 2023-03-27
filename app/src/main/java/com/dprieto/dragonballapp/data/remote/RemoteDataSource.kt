package com.dprieto.dragonballapp.data.remote

interface RemoteDataSource {

    suspend fun  doLogin(): String

}