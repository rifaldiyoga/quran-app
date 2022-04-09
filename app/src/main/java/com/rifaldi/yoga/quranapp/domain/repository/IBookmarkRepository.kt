package com.rifaldi.yoga.quranapp.domain.repository

import com.rifaldi.yoga.quranapp.data.source.local.model.BookmarkEntity
import com.rifaldi.yoga.quranapp.domain.model.AyahModel
import com.rifaldi.yoga.quranapp.domain.model.SurahModel
import com.rifaldi.yoga.quranapp.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by aldi on 07/04/22.
 */
interface IBookmarkRepository {

    fun getBookmarkList() : Flow<Resource<List<BookmarkEntity>?>>

    suspend fun addSurah(model : SurahModel, ayah : AyahModel)

    suspend fun deleteSurah(model: BookmarkEntity)

}