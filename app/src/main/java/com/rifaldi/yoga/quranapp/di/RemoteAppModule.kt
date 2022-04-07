package com.rifaldi.yoga.quranapp.di

import com.rifaldi.yoga.quranapp.data.source.remote.ApiUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by aldi on 04/04/22.
 */
@Module
@InstallIn(SingletonComponent::class)
object RemoteAppModule {

    @Provides
    @Singleton
    fun provideRetrofitClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.quran.sutanlab.id/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiUtils(retrofit: Retrofit) : ApiUtils {
        return ApiUtils(retrofit)
    }

}