package com.dprieto.dragonballapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
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

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences{
        val masterKey: MasterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return  EncryptedSharedPreferences.create(
            context,
            "secret_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

}