package com.dprieto.dragonballapp.data

import com.dprieto.dragonballapp.data.mappers.ResponseToPresentationDetailMapper
import com.dprieto.dragonballapp.data.mappers.ResponseToPresentationMapper
import com.dprieto.dragonballapp.data.remote.RemoteDataSource
import com.dprieto.dragonballapp.ui.detail.HeroDetailState
import com.dprieto.dragonballapp.ui.herolist.HeroListState
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val remoteDataSource: RemoteDataSource,
                                        private val remoteToPresentationMapper: ResponseToPresentationMapper,
                                        private val remoteToPresentationDetailMapper: ResponseToPresentationDetailMapper): Repository {

    override suspend fun doLogin(): String {
        return remoteDataSource.doLogin()
    }


    override suspend fun getHeros(): HeroListState {

        val result = remoteDataSource.getHeros()
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

    override suspend fun getHeroDetail(name: String): HeroDetailState {
        val result = remoteDataSource.getHeroDetail(name)
        return when {
            result.isSuccess -> {
                result.getOrNull()?.let {
                    HeroDetailState.Success(remoteToPresentationDetailMapper.map(it))
                }?: HeroDetailState.Error(
                    "No existe el heroe"
                )
            }
            else -> {
                when(val exception = result.exceptionOrNull()){
                    is HttpException -> HeroDetailState.NetworkError(
                        exception.code()
                    )
                    else -> {
                        HeroDetailState.Error(
                            result.exceptionOrNull()?.message
                        )
                    }
                }
            }
        }
    }

    override suspend fun getLocations(id: String): HeroDetailState {
        TODO("Not yet implemented")
    }

    override suspend fun setFavorite(): HeroDetailState {
        TODO("Not yet implemented")
    }
}