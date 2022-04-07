package com.rifaldi.yoga.quranapp.data.source.remote.response

import android.os.Parcelable
import com.rifaldi.yoga.quranapp.domain.model.SurahModel
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Created by aldi on 04/04/22.
 */
@Parcelize
data class ListAyahResponse (
    @Json(name = "status")
    var status : String = "",
    @Json(name = "data")
    var data : SurahModel
) : Parcelable