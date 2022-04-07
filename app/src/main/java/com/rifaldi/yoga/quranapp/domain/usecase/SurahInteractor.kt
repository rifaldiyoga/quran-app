package com.rifaldi.yoga.quranapp.domain.usecase

import com.rifaldi.yoga.quranapp.data.source.repository.SurahRepository
import com.rifaldi.yoga.quranapp.domain.model.AyahModel
import com.rifaldi.yoga.quranapp.domain.model.SurahModel
import com.rifaldi.yoga.quranapp.domain.repository.ISurahRepository
import com.rifaldi.yoga.quranapp.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by aldi on 05/04/22.
 */
class SurahInteractor @Inject constructor(
    private val repository: SurahRepository
) : ISurahRepository {

    override fun getSurahList(): Flow<Resource<List<SurahModel>>> = repository.getSurahList()

    override fun getAyahList(number: Int): Flow<Resource<List<AyahModel>>> = repository.getAyahList(number)

    override fun getLastRead(): Flow<Resource<SurahModel?>> = repository.getLastRead()

}