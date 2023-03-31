package com.dprieto.dragonballapp.data

import com.dprieto.dragonballapp.data.local.LocalDataSource
import com.dprieto.dragonballapp.data.mappers.*
import com.dprieto.dragonballapp.data.remote.RemoteDataSource
import com.dprieto.dragonballapp.ui.detail.HeroDetailState
import com.dprieto.dragonballapp.ui.herolist.HeroListState
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val remoteDataSource: RemoteDataSource,
                                        private val remoteToPresentationMapper: ResponseToPresentationMapper,
                                        private val localToPresentationDetailMapper: LocalToPresentationDetailMapper,
                                        private val remoteToPresentationDetailMapper: ResponseToPresentationDetailMapper,
                                        private val localDataSource: LocalDataSource,
                                        private val remoteToLocalMapper: RemoteToLocalMapper,
                                        private val localToPresentationMapper: LocalToPresentationMapper): Repository {

    override suspend fun doLogin(): String {
        return remoteDataSource.doLogin()
    }

    override suspend fun getHeros(): HeroListState {

        //Pido datos a local
        val localHeros = localDataSource.getHeros()

        if(localHeros.getOrThrow().isEmpty()){
            val remoteHeros = remoteDataSource.getHeros()

            localDataSource.insertHeros(remoteToLocalMapper.map(remoteHeros.getOrThrow()))
        }

        return when {
            localHeros.isSuccess -> HeroListState.Success(localToPresentationMapper.map(localDataSource.getHeros().getOrThrow()))

            else -> {
                HeroListState.Error(
                    localHeros.exceptionOrNull()?.message
                )
            }
        }
    }

    override suspend fun getHeroDetail(name: String): HeroDetailState {
        val result = remoteDataSource.getHeroDetail(name)
        return when {
            result.isSuccess -> {
                result.getOrNull()?.let {
                    HeroDetailState.SuccessDetail(remoteToPresentationDetailMapper.map(it))
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
        val result = remoteDataSource.getLocations(id)
        return when {
            result.isSuccess -> {
                result.getOrNull()?.let {
                    HeroDetailState.SuccessLocations(it)
                }?: HeroDetailState.Error(
                    "No existen ubicaciones"
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

    override suspend fun setFavorite(id: String, name: String): HeroDetailState {
        val result = remoteDataSource.setFavorite(id)

        when {
            result.isSuccess -> {
                //Volver a solicitar los heroes
                val resultGetHero = remoteDataSource.getHeroDetail(name)

                when{

                    resultGetHero.isSuccess -> {

                        resultGetHero.getOrNull()?.let {
                            //Guardar en local
                            localDataSource.updateHero(remoteToLocalMapper.map(it))

                            //Devolver heroe
                            return HeroDetailState.SuccessDetail(localToPresentationDetailMapper.map(localDataSource.getHero(it.id)))

                        }?: return HeroDetailState.Error(
                            "Error al actualizar el heroe"
                        )
                    }

                    else -> {
                        return HeroDetailState.Error("Error al solicitar heroe")
                    }
                }
            }
            else -> {
                return HeroDetailState.Error("Error al guardar favorito")
            }
        }

    }
}