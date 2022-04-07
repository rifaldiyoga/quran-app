package com.rifaldi.yoga.quranapp.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by aldi on 06/04/22.
 */
@Entity(tableName = "last_read")
data class LastReadEntitiy(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 1,
    @ColumnInfo(name = "surah_name")
    var surahName : String = "",
    @ColumnInfo(name = "surah_name_arabic")
    var surahNameArabic : String = "",
    @ColumnInfo(name = "translation")
    var translation : String = "",
    @ColumnInfo(name = "number")
    var number: Int = -1,
    @ColumnInfo(name = "revelation")
    var revelation : String = "",
    @ColumnInfo(name = "numberOfVerses")
    var numberOfVerses : Int = -1
)