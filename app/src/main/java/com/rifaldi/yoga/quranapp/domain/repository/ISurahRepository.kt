package com.rifaldi.yoga.quranapp.domain.repository

import com.rifaldi.yoga.quranapp.domain.model.AyahModel
import com.rifaldi.yoga.quranapp.domain.model.SurahModel
import com.rifaldi.yoga.quranapp.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by aldi on 05/04/22.
 */
interface ISurahRepository {

    fun getSurahList() : Flow<Resource<List<SurahModel>>>

    fun getAyahList(number : Int) : Flow<Resource<List<AyahModel>>>

    fun getLastRead() : Flow<Resource<SurahModel?>>
}