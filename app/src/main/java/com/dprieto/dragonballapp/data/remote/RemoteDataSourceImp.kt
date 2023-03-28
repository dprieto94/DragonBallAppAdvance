package com.dprieto.dragonballapp.data.remote

import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val api: DragonBallApi): RemoteDataSource {

    override suspend fun  doLogin(user: String, pass: String): String{
        return api.doLogin()
    }

}