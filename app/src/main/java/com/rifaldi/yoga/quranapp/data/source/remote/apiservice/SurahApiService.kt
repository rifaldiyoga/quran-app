package com.rifaldi.yoga.quranapp.data.source.remote.apiservice

import com.rifaldi.yoga.quranapp.data.source.remote.response.ListAyahResponse
import com.rifaldi.yoga.quranapp.data.source.remote.response.SurahResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by aldi on 04/04/22.
 */
interface SurahApiService {

    @GET("surah")
    suspend fun getSurahList() : SurahResponse

    @GET("surah/{number}")
    suspend fun getSurahList(@Path("number") number : Int) : ListAyahResponse

}