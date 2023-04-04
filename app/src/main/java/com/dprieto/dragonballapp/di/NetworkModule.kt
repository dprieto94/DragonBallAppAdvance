package com.dprieto.dragonballapp.di

import android.content.SharedPreferences
import com.dprieto.dragonballapp.data.remote.DragonBallApi
import com.dprieto.dragonballapp.ui.login.LoginFragment
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideCredentials(sharedPreferences: SharedPreferences): String{

        return if (LoginFragment.isUserLoggedInApp)
            //Access to sharedpreferences
            sharedPreferences.getString("token", "").toString()

        else {
            val user = sharedPreferences.getString("user", "").toString()
            val pass = sharedPreferences.getString("pass", "").toString()

            return Credentials.basic(user, pass)
        }
     }

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
    fun provideOkHttpClient(httpLogginInterceptor: HttpLoggingInterceptor, credentials: String): OkHttpClient {

        return if (LoginFragment.isUserLoggedInApp)

            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val originalRequest = chain.request()

                    val newRequest = originalRequest.newBuilder()
                        .header("Authorization", "Bearer $credentials")
                        .header("Content-Type", "application/json")
                        .build()

                    chain.proceed(newRequest)
                }
                .addInterceptor(httpLogginInterceptor)
                .build()
        else

            OkHttpClient.Builder()
                .addInterceptor{ chain ->
                    val originalRequest = chain.request()
                    val newRequest = originalRequest.newBuilder().header("Authorization", credentials).build()
                    chain.proceed(newRequest)
                }
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