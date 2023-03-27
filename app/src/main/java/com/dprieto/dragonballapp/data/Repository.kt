package com.dprieto.dragonballapp.data

interface Repository {

    suspend fun doLogin(): String

}