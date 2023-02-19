package com.giftech.quranku_xml.ui.surah

import androidx.lifecycle.*
import com.giftech.quranku_xml.data.MainRepository
import com.giftech.quranku_xml.data.model.Ayat
import com.giftech.quranku_xml.data.model.LastRead
import com.giftech.quranku_xml.data.model.Surah
import com.giftech.quranku_xml.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurahViewModel
@Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private var _surah = MutableLiveData<Surah>()
    val surah:LiveData<Surah> = _surah

    private var _lastRead = MutableLiveData<LastRead>()
    val lastRead = _lastRead

    private fun getLastRead(){
        viewModelScope.launch {
            repository.getLastRead().collect{
                _lastRead.postValue(it)
            }
        }
    }

    fun setLastRead(lastRead: LastRead){
        _lastRead.postValue(lastRead)
        repository.setLastRead(lastRead)
    }

    fun setNomorSurah(nomor:Int){
        viewModelScope.launch {
            repository.getDetailSurah(nomor).collect{
                _surah.value = it
            }
        }
    }

    init {
        getLastRead()
    }

}