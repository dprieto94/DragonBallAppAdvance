package com.dprieto.dragonballapp.di

import com.dprieto.dragonballapp.data.Repository
import com.dprieto.dragonballapp.data.RepositoryImp
import com.dprieto.dragonballapp.data.remote.RemoteDataSource
import com.dprieto.dragonballapp.data.remote.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repositoryImp: RepositoryImp): Repository

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImp): RemoteDataSource

}