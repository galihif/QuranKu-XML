package com.giftech.quranku_xml.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giftech.quranku_xml.data.MainRepository
import com.giftech.quranku_xml.data.model.LastRead
import com.giftech.quranku_xml.data.model.Surah
import com.giftech.quranku_xml.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
   private val repo:MainRepository
) : ViewModel() {

    private var _listSurah:MutableLiveData<Resource<List<Surah>>> = MutableLiveData()
    val listSurah:LiveData<Resource<List<Surah>>> = _listSurah

    private var _lastRead = MutableLiveData<LastRead>()
    val lastRead = _lastRead

    private fun getListSurah(){
        viewModelScope.launch {
            repo.getListSurah().collect{
                _listSurah.value = it
            }
        }
    }

    private fun getLastRead() {
        viewModelScope.launch {
            repo.getLastRead().collect{
                _lastRead.postValue(it)
            }
        }
    }


    init {
        getListSurah()
        getLastRead()
    }
}