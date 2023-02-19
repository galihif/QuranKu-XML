package com.giftech.quranku_xml.ui.surah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.giftech.quranku_xml.R
import com.giftech.quranku_xml.databinding.ActivitySurahBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SurahActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySurahBinding
    private val viewModel:SurahViewModel by viewModels()
    private lateinit var ayatAdapter: AyatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ayatAdapter = AyatAdapter {
            viewModel.setLastRead(it)
        }
        binding.rvAyat.adapter = ayatAdapter

        if (intent.extras != null){
            val nomorSurah = intent.extras!!.getInt("nomorSurah",0)
            viewModel.setNomorSurah(nomorSurah)

            viewModel.lastRead.observe(this){
                ayatAdapter.setLastRead(it)
            }

            viewModel.surah.observe(this){
                ayatAdapter.setList(it.listAyat!!)
                with(binding){
                    surahDetail.tvSurahName.text = it.nama
                    surahDetail.tvSurahArti.text = it.arti
                    surahDetail.tvSurahTypeAyat.text = it.type

                    tvSurahName.text = it.nama
                    tvSurahName.setOnClickListener {
                        onBackPressed()
                    }
                }
            }
        }
    }
}