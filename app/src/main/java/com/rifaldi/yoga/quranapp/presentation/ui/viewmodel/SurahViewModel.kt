package com.rifaldi.yoga.quranapp.presentation.ui.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.*
import com.rifaldi.yoga.quranapp.data.source.local.model.BookmarkEntity
import com.rifaldi.yoga.quranapp.domain.model.AyahModel
import com.rifaldi.yoga.quranapp.domain.model.SurahModel
import com.rifaldi.yoga.quranapp.domain.usecase.BookmarkInteractor
import com.rifaldi.yoga.quranapp.domain.usecase.SurahInteractor
import com.rifaldi.yoga.quranapp.presentation.ui.state.SurahState
import com.rifaldi.yoga.quranapp.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by aldi on 04/04/22.
 */
@HiltViewModel
class SurahViewModel @Inject constructor(
    private val surahInteractor: SurahInteractor,
    private val bookmarkInteractor: BookmarkInteractor
) : ViewModel() {

    private var _state = MutableStateFlow(SurahState())
    var state = _state.asStateFlow()

    private var surahList: MediatorLiveData<Resource<List<AyahModel>>> = MediatorLiveData()
    fun observeSurahList() = surahList as LiveData<Resource<List<AyahModel>>>

    fun setSurah(model : SurahModel) = viewModelScope.launch {
        _state.update {
            it.copy(surah = model)
        }
    }

    fun getList(number : Int) = viewModelScope.launch {
        val source = surahInteractor.getAyahList(number).asLiveData()
        surahList.addSource(source){
            if (it != null) {
                surahList.value = it
                if (it.status === Resource.Status.SUCCESS || it.status === Resource.Status.ERROR) {
                    surahList.removeSource(source)
                }
            } else {
                surahList.removeSource(source)
            }
        }
    }

    fun addBookmark(model: AyahModel) = viewModelScope.launch {
        bookmarkInteractor.addSurah(state.value.surah!!, model)

    }

    fun deleteBookmark(model: AyahModel) = viewModelScope.launch {
        bookmarkInteractor.deleteSurah(BookmarkEntity(numberOfAyah = model.number.inQuran))
    }

}