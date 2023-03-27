package com.dprieto.dragonballapp.data

import com.dprieto.dragonballapp.data.remote.RemoteDataSource

class RepositoryImp(private val remoteDataSource: RemoteDataSource): Repository {

    override suspend fun doLogin(): String {
        return remoteDataSource.doLogin()
    }
}