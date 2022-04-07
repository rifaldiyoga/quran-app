package com.rifaldi.yoga.quranapp.di

import android.content.Context
import androidx.room.Room
import com.rifaldi.yoga.quranapp.data.source.local.QuranDatabase
import com.rifaldi.yoga.quranapp.data.source.local.dao.LastReadDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by aldi on 06/04/22.
 */
@Module
@InstallIn(SingletonComponent::class)
object LocalAppModule {

    @Provides
    @Singleton
    fun provideDbHelper(@ApplicationContext context : Context) : QuranDatabase {
        return Room.databaseBuilder(
            context,
            QuranDatabase::class.java,
            QuranDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLastReadDao(quranDatabase: QuranDatabase) : LastReadDao {
        return quranDatabase.lastReadDao
    }

}