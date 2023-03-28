package com.dprieto.dragonballapp.data.remote

interface RemoteDataSource {

    suspend fun  doLogin(user: String, pass: String): String

}