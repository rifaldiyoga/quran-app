package com.rifaldi.yoga.quranapp.domain.usecase

import com.rifaldi.yoga.quranapp.data.source.local.model.BookmarkEntity
import com.rifaldi.yoga.quranapp.data.source.repository.BookmarkRepository
import com.rifaldi.yoga.quranapp.domain.model.AyahModel
import com.rifaldi.yoga.quranapp.domain.model.SurahModel
import com.rifaldi.yoga.quranapp.domain.repository.IBookmarkRepository
import com.rifaldi.yoga.quranapp.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by aldi on 07/04/22.
 */

class BookmarkInteractor @Inject constructor(private val bookmarkRepository: BookmarkRepository) : IBookmarkRepository {

    override fun getBookmarkList(): Flow<Resource<List<BookmarkEntity>?>> = bookmarkRepository.getBookmarkList()

    override suspend fun addSurah(model: SurahModel, ayah : AyahModel) = bookmarkRepository.addSurah(model, ayah)

    override suspend fun deleteSurah(model: BookmarkEntity) = bookmarkRepository.deleteSurah(model)
}