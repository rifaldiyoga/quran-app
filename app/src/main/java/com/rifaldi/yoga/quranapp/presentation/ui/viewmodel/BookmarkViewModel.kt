package com.rifaldi.yoga.quranapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rifaldi.yoga.quranapp.data.source.local.model.BookmarkEntity
import com.rifaldi.yoga.quranapp.domain.usecase.BookmarkInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by aldi on 07/04/22.
 */
@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkInteractor: BookmarkInteractor
) : ViewModel() {


    var bookmarkList = bookmarkInteractor.getBookmarkList()

    fun deleteAyah(model : BookmarkEntity) = viewModelScope.launch {
        bookmarkInteractor.deleteSurah(model)
    }

}