package com.rifaldi.yoga.quranapp.presentation.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.rifaldi.yoga.quranapp.databinding.ItemTitleSurahBinding
import com.rifaldi.yoga.quranapp.domain.model.SurahModel

/**
 * Created by aldi on 05/04/22.
 */
class TitleViewHolder(private val binding : ItemTitleSurahBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model : SurahModel){
        binding.apply {
            tvSurahTitle.text = model.name.transliteration.en
            tvSurahTranslate.text = model.name.translation.en
            tvSurahDesc.text = "${model.revelation.en} - ${model.numberOfVerses} VERSES"
        }
    }

}