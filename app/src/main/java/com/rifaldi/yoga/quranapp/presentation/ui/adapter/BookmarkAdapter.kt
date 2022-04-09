package com.rifaldi.yoga.quranapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rifaldi.yoga.quranapp.data.source.local.model.BookmarkEntity
import com.rifaldi.yoga.quranapp.databinding.ItemBookmarkBinding

/**
 * Created by aldi on 05/04/22.
 */
class BookmarkAdapter(val onBookmarkClick : (BookmarkEntity) -> Unit) : ListAdapter<BookmarkEntity, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            else -> ViewHolder(ItemBookmarkBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = getItem(holder.absoluteAdapterPosition)
        when(holder.itemViewType){
            else -> {
                val viewHolder = holder as ViewHolder
                viewHolder.bind(model)
            }
        }
    }

    inner class ViewHolder(private val binding: ItemBookmarkBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model: BookmarkEntity) {
            binding.apply {
                tvNumber.text = model.numberOfVerses.toString()
                tvAyah.text = model.ayahText
                tvTranslate.text = model.ayahTranslation
                tvSurahTitle.text = model.surahName
                imageView4.setOnClickListener {
                    onBookmarkClick(model)
                }
            }
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<BookmarkEntity>(){

        override fun areItemsTheSame (
            oldItem: BookmarkEntity,
            newItem: BookmarkEntity
        ) = oldItem.numberOfAyah == newItem.numberOfAyah
        override fun areContentsTheSame(
            oldItem: BookmarkEntity,
            newItem: BookmarkEntity
        ) = oldItem == newItem
    }
}