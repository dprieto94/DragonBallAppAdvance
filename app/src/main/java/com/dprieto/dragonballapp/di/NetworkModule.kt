package com.dprieto.dragonballapp.di

import com.dprieto.dragonballapp.data.remote.DragonBallApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideMoshi(): Moshi {
        return  Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideOkHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(httpLogginInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return  OkHttpClient.Builder()
            .addInterceptor { chain ->
                //Este interractor modifica la header
                val originalRequest = chain.request()

                val newRequest = originalRequest.newBuilder()
                    //.header("Authorization", "Bearer $TOKEN")
                    .header("Content-Type", "application/json")
                    .build()

                chain.proceed(newRequest)
            }
            //Se ejecuta cuando da un error (Se baraja response con la 401)
                /*
            .authenticator{ _, response ->
                response.request.newBuilder().header("Authorization", "Bearer ${SuperHeroListViewModel.TOKEN}").build()
            }
                 */
            //Este inteactor muestra los logs
            .addInterceptor(httpLogginInterceptor)
            .build()
    }


    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dragonball.keepcoding.education/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }


    @Provides
    fun provideApi(retrofit: Retrofit): DragonBallApi {
        return retrofit.create(DragonBallApi::class.java)
    }

}