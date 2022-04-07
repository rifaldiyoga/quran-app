package com.rifaldi.yoga.quranapp.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.rifaldi.yoga.quranapp.domain.usecase.SurahInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by aldi on 04/04/22.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val surahInteractor: SurahInteractor
) : ViewModel() {

    var surahList = surahInteractor.getSurahList()

    var lastRead = surahInteractor.getLastRead()

}