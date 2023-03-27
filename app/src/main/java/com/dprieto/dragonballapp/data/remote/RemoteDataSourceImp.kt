package com.dprieto.dragonballapp.data.remote

class RemoteDataSourceImp(private val api: DragonBallApi): RemoteDataSource {

    override suspend fun  doLogin(): String{
        return api.doLogin()
    }

}