package com.dprieto.dragonballapp.data

import com.dprieto.dragonballapp.data.remote.RemoteDataSource
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val remoteDataSource: RemoteDataSource): Repository {

    override suspend fun doLogin(user: String, pass: String): String {
        return remoteDataSource.doLogin(user, pass)
    }
}