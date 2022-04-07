package com.rifaldi.yoga.quranapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rifaldi.yoga.quranapp.databinding.ItemAyahBinding
import com.rifaldi.yoga.quranapp.databinding.ItemTitleSurahBinding
import com.rifaldi.yoga.quranapp.domain.model.AyahModel
import com.rifaldi.yoga.quranapp.domain.model.SurahModel
import com.rifaldi.yoga.quranapp.presentation.ui.adapter.viewholder.TitleViewHolder

/**
 * Created by aldi on 05/04/22.
 */
class AyahAdapter(private val model: SurahModel) : ListAdapter<AyahModel, RecyclerView.ViewHolder>(DiffCallback()) {

    private val TITLE_ITEM = 1
    private val AYAH_ITEM = 0

    override fun submitList(list: MutableList<AyahModel>?) {
        list?.add(0, AyahModel())
        super.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            TITLE_ITEM -> TitleViewHolder(ItemTitleSurahBinding.inflate(inflater, parent, false))
            else -> ViewHolder(ItemAyahBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            AYAH_ITEM -> {
                val viewHolder = holder as ViewHolder
                viewHolder.bind(getItem(position))
            }
            else -> {
                val viewHolder = holder as TitleViewHolder
                viewHolder.bind(model)
            }
        }
    }

    inner class ViewHolder(private val binding: ItemAyahBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model: AyahModel){
            binding.apply {
                tvNumber.text = model.number.inSurah.toString()
                tvAyah.text = model.text.arab
                tvTranslate.text = model.translation.en
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> TITLE_ITEM
            else -> AYAH_ITEM
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<AyahModel>(){

        override fun areItemsTheSame(
            oldItem: AyahModel,
            newItem: AyahModel
        ) = oldItem.number == newItem.number
        override fun areContentsTheSame(
            oldItem: AyahModel,
            newItem: AyahModel
        ) = oldItem == newItem
    }
}