package com.rifaldi.yoga.quranapp.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
* Created by aldi on 06/04/22.
*/
@Entity(tableName = "bookmark")
data class BookmarkEntity (
    @PrimaryKey(autoGenerate = false)
    var numberOfAyah : Int = -1,
    @ColumnInfo(name = "surah_name")
    var surahName : String = "",
    @ColumnInfo(name = "surah_name_arabic")
    var surahNameArabic : String = "",
    @ColumnInfo(name = "ayah_text")
    var ayahText : String = "",
    @ColumnInfo(name = "ayah_translation")
    var ayahTranslation : String = "",
    @ColumnInfo(name = "numberOfVerses")
    var numberOfVerses : Int = -1,
)