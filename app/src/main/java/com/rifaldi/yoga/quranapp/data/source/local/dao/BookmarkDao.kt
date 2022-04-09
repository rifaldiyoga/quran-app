package com.rifaldi.yoga.quranapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rifaldi.yoga.quranapp.data.source.local.model.BookmarkEntity
import com.rifaldi.yoga.quranapp.data.source.local.model.LastReadEntitiy

/**
 * Created by aldi on 06/04/22.
 */
@Dao
interface BookmarkDao {

    @Insert(onConflict = REPLACE)
    fun insert(model : BookmarkEntity)

    @Query("SELECT * FROM bookmark")
    fun getBookmarkList() : List<BookmarkEntity>?

    @Query("SELECT * FROM bookmark WHERE surah_name = :surahName")
    fun getBookmarkBySurahList(surahName : String) : List<BookmarkEntity>?

    @Query("DELETE FROM bookmark WHERE numberOfAyah = :id")
    fun delete(id : Int)

}