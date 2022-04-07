package com.rifaldi.yoga.quranapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rifaldi.yoga.quranapp.data.source.local.dao.LastReadDao
import com.rifaldi.yoga.quranapp.data.source.local.model.LastReadEntitiy

/**
 * Created by aldi on 06/04/22.
 */
@Database(entities = [LastReadEntitiy::class], version = 1, exportSchema = false)
abstract class QuranDatabase : RoomDatabase() {

    abstract val lastReadDao : LastReadDao

    companion object {
        const val DATABASE_NAME = "quran_app.db"
    }

}