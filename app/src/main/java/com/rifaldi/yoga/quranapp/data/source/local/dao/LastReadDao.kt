package com.rifaldi.yoga.quranapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rifaldi.yoga.quranapp.data.source.local.model.LastReadEntitiy

/**
 * Created by aldi on 06/04/22.
 */
@Dao
interface LastReadDao {

    @Insert(onConflict = REPLACE)
    fun insertLastRead(model : LastReadEntitiy)

    @Query("SELECT * FROM last_read limit 1")
    fun getLastRead() : LastReadEntitiy?

}