package com.giftech.quranku_xml.ui.welcome

import androidx.lifecycle.ViewModel
import com.giftech.quranku_xml.data.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel
@Inject constructor(
    private val repo:MainRepository
) : ViewModel() {
}