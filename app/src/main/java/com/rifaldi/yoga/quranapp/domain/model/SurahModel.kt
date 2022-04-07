package com.rifaldi.yoga.quranapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by aldi on 04/04/22.
 */
@Parcelize
data class SurahModel(
    var number : Int = -1,
    var name : Name = Name(),
    var revelation : Revelation = Revelation(),
    var preBismillah : PreBismillah?,
    var numberOfVerses : Int = -1,
    var verses : List<AyahModel>? = null
) : Parcelable {

    @Parcelize
    data class Name(
        var short : String = "",
        var transliteration : Transliteration = Transliteration(),
        var translation : Translation = Translation(),
        ) : Parcelable

    @Parcelize
    data class Transliteration(
        var en : String = "",
        var id : String = "",
    ) : Parcelable

    @Parcelize
    data class Translation(
        var en : String = "",
        var id : String = "",
    ) : Parcelable

    @Parcelize
    data class Revelation(
        var en : String = "",
        var id : String = "",
    ) : Parcelable

    @Parcelize
    data class PreBismillah(
        var text : Text,
    ) : Parcelable {

        @Parcelize
        data class Text(
            var arab : String = "",
        ):Parcelable

    }

}