package com.giftech.quranku_xml.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.giftech.quranku_xml.R
import com.giftech.quranku_xml.databinding.ActivityHomeBinding
import com.giftech.quranku_xml.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel:HomeViewModel by viewModels()
    private lateinit var surahAdapter: SurahAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        surahAdapter = SurahAdapter()
        binding.rvSurah.adapter = surahAdapter

        viewModel.listSurah.observe(this){
            when(it){
                is Resource.Error -> {
                    Log.d("galih", "onCreate: ${it.message}")
                }
                is Resource.Loading -> {
                    Log.d("galih", "onCreate: loading")
                }
                is Resource.Success -> {
                    surahAdapter.setList(it.data)
                }
            }
        }
    }
}