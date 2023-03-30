package com.dprieto.dragonballapp.data

import com.dprieto.dragonballapp.data.mappers.ResponseToPresentationMapper
import com.dprieto.dragonballapp.data.remote.RemoteDataSource
import com.dprieto.dragonballapp.ui.herolist.HeroListState
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val remoteDataSource: RemoteDataSource,
                                        private val remoteToPresentationMapper: ResponseToPresentationMapper): Repository {

    override suspend fun doLogin(): String {
        return remoteDataSource.doLogin()
    }


    override suspend fun getHeros(name: String): HeroListState {

        val result = remoteDataSource.getHeros(name)
        return when {
            result.isSuccess -> HeroListState.Success(remoteToPresentationMapper.map(result.getOrThrow()))

            else -> {
                when(val exception = result.exceptionOrNull()){
                    is HttpException -> HeroListState.NetworkError(
                        exception.code()
                    )
                    else -> {
                        HeroListState.Error(
                            result.exceptionOrNull()?.message
                        )
                    }
                }
            }
        }
    }
}