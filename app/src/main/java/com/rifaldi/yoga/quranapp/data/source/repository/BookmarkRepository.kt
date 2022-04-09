package com.rifaldi.yoga.quranapp.data.source.repository

import com.rifaldi.yoga.quranapp.data.source.local.dao.BookmarkDao
import com.rifaldi.yoga.quranapp.data.source.local.model.BookmarkEntity
import com.rifaldi.yoga.quranapp.domain.model.AyahModel
import com.rifaldi.yoga.quranapp.domain.model.SurahModel
import com.rifaldi.yoga.quranapp.domain.repository.IBookmarkRepository
import com.rifaldi.yoga.quranapp.presentation.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by aldi on 07/04/22.
 */
class BookmarkRepository @Inject constructor(
    private val bookmarkDao: BookmarkDao
): IBookmarkRepository {

    override fun getBookmarkList(): Flow<Resource<List<BookmarkEntity>?>> = channelFlow{
        withContext(Dispatchers.IO){
            send(Resource.success(bookmarkDao.getBookmarkList()))
        }
    }

    override suspend fun addSurah(surah: SurahModel, ayah: AyahModel) {
        withContext(Dispatchers.IO){
            val model = BookmarkEntity(
                surahName = surah.name.transliteration.en,
                surahNameArabic = surah.name.short,
                ayahText = ayah.text.arab,
                ayahTranslation = ayah.translation.en,
                numberOfVerses = ayah.number.inSurah,
                numberOfAyah = ayah.number.inQuran)
            bookmarkDao.insert(model)
        }
    }

    override suspend fun deleteSurah(model: BookmarkEntity) {
        withContext(Dispatchers.IO){
            bookmarkDao.delete(model.numberOfAyah)
        }
    }


}