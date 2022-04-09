package com.rifaldi.yoga.quranapp.presentation.utils

import android.content.Context
import android.content.Intent
import com.rifaldi.yoga.quranapp.domain.model.AyahModel
import com.rifaldi.yoga.quranapp.domain.model.SurahModel

/**
 * Created by aldi on 08/04/22.
 */
class Utils {
    companion object{
        fun sendText(context: Context ,ayahText : String, ayahTranslation : String, surahName : String, ayahNumber : Int){
            val ayah = "$ayahText \n\"$ayahTranslation\". ($surahName : $ayahNumber)"

            val intent = Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, ayah)

            // Check if there's an app that can handle this intent before launching it
            if (context.packageManager?.resolveActivity(intent, 0) != null) {
                // Start a new activity with the given intent (this may open the share dialog on a
                // device if multiple apps can handle this intent)
                context.startActivity(intent)
            }

        }
    }
}