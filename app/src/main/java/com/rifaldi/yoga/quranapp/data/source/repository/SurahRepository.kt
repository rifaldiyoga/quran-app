package com.rifaldi.yoga.quranapp.data.source.repository

import com.rifaldi.yoga.quranapp.data.source.local.dao.LastReadDao
import com.rifaldi.yoga.quranapp.data.source.local.model.LastReadEntitiy
import com.rifaldi.yoga.quranapp.data.source.remote.ApiUtils
import com.rifaldi.yoga.quranapp.domain.model.AyahModel
import com.rifaldi.yoga.quranapp.domain.model.SurahModel
import com.rifaldi.yoga.quranapp.domain.repository.ISurahRepository
import com.rifaldi.yoga.quranapp.presentation.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by aldi on 05/04/22.
 */
class SurahRepository @Inject constructor(
    private val apiUtils: ApiUtils,
    private val lastReadDao: LastReadDao
) : ISurahRepository {

    override fun getSurahList(): Flow<Resource<List<SurahModel>>> = flow {
        emit(Resource.loading())
        try {
            emit(Resource.success(apiUtils.getSurahApiService().getSurahList().data))
        } catch (e : Exception) {
            emit(Resource.error(null, e.message!!, 1))
        }
    }

    override fun getAyahList(number: Int): Flow<Resource<List<AyahModel>>> = flow{
        emit(Resource.loading())
        try {
            val data = apiUtils.getSurahApiService().getSurahList(number).data

            withContext(Dispatchers.IO){
                val model = LastReadEntitiy(
                    id = 1,
                    surahName = data.name.transliteration.en,
                    translation = data.name.translation.en,
                    number = data.number,
                    revelation = data.revelation.en,
                    numberOfVerses = data.numberOfVerses,
                    surahNameArabic = data.name.short)
                lastReadDao.insertLastRead(model)
            }

            emit(Resource.success(data.verses!!))
        } catch (e : Exception){
            emit(Resource.error(null, e.message!!, 1))
        }
    }

    override fun getLastRead(): Flow<Resource<SurahModel?>> = channelFlow{
        withContext(Dispatchers.IO){
            try {
                val data = lastReadDao.getLastRead()
                data?.let {
                    var model = SurahModel(
                        data.number,
                        SurahModel.Name(
                            short = data.surahNameArabic,
                            translation = SurahModel.Translation(en = data.translation),
                            transliteration = SurahModel.Transliteration(en = data.surahName)
                        ),
                        numberOfVerses = data.numberOfVerses,
                        revelation = SurahModel.Revelation(en = data.revelation),
                        preBismillah = null,

                        )
                    send(Resource.success(model))
                } ?: run {
                    send(Resource.success(null))
                }

            }catch (e : Exception){
                send(Resource.error(null, e.message!!, 1))
            }
        }
    }

}