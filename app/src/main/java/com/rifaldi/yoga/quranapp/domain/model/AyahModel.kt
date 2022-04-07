package com.rifaldi.yoga.quranapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by aldi on 04/04/22.
 */
@Parcelize
data class AyahModel(
    var number : Number = Number(),
    var text : Text = Text(),
    var translation: Translation = Translation(),
    var audio : Audio = Audio(),
) : Parcelable {

    @Parcelize
    data class Number(
        var inSurah : Int = -1
    ) : Parcelable

    @Parcelize
    data class Text(
        var arab : String = "",
        var transliteration : Transliteration = Transliteration(),
    ):Parcelable {

        @Parcelize
        data class Transliteration(
            var en : String = "",
        ) : Parcelable

    }

    @Parcelize
    data class Translation(
        var en : String = "",
        var id : String = "",
    ) : Parcelable

    @Parcelize
    data class Audio(
        var primary : String = "",
    ) : Parcelable


}