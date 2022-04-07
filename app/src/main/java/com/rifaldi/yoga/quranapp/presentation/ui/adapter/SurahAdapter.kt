package com.rifaldi.yoga.quranapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rifaldi.yoga.quranapp.databinding.ItemSurahBinding
import com.rifaldi.yoga.quranapp.domain.model.SurahModel

/**
 * Created by aldi on 04/04/22.
 */
class SurahAdapter(val onItemClick : (SurahModel) -> Unit ) : ListAdapter<SurahModel, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemSurahBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemSurahBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: SurahModel){
            binding.apply {
                tvNumber.text = model.number.toString()
                tvSurahTitle.text = model.name.transliteration.en
                tvSurahDesc.text = "${model.revelation.en} - ${model.numberOfVerses} VERSES"
                tvSurahTitleArabic.text = model.name.short
                clSurah.setOnClickListener {
                    onItemClick(model)
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<SurahModel>() {

        override fun areItemsTheSame(
            oldItem: SurahModel,
            newItem: SurahModel
        ) = oldItem.number == newItem.number

        override fun areContentsTheSame(
            oldItem: SurahModel,
            newItem: SurahModel
        ) = oldItem == newItem
    }
}