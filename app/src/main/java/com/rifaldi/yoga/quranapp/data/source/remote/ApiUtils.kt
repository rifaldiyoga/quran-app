package com.rifaldi.yoga.quranapp.data.source.remote

import com.rifaldi.yoga.quranapp.data.source.remote.apiservice.SurahApiService
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by aldi on 25/02/22.
 */

class ApiUtils @Inject constructor(private val retrofit : Retrofit) {

    fun getSurahApiService() : SurahApiService {
        return retrofit.create(SurahApiService::class.java)
    }

}