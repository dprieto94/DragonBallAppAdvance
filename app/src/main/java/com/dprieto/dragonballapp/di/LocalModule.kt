package com.dprieto.dragonballapp.di

import android.content.Context
import androidx.room.Room
import com.dprieto.dragonballapp.data.local.HeroDAO
import com.dprieto.dragonballapp.data.local.HeroDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HeroDatabase{
        return Room.databaseBuilder(
            context,
            HeroDatabase::class.java, "dragon-ball-database"
        ).build()

    }

    @Provides
    fun provideDao(database: HeroDatabase): HeroDAO {
        return database.getDAO()
    }

}