package com.dprieto.dragonballapp.data

interface Repository {

    suspend fun doLogin(user: String, pass: String): String

}