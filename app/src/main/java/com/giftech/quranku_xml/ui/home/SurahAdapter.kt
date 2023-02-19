package com.giftech.quranku_xml.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftech.quranku_xml.R
import com.giftech.quranku_xml.data.model.LastRead
import com.giftech.quranku_xml.data.model.Surah
import com.giftech.quranku_xml.databinding.ItemSurahBinding

class SurahAdapter : RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    private var listSurah = ArrayList<Surah>()
    private lateinit var lastReadAyat: LastRead

    fun setList(list: List<Surah>?) {
        listSurah.clear()
        if (list != null) {
            listSurah.addAll(list)
        }
        notifyDataSetChanged()
    }

    fun setLastRead(ayat: LastRead) {
        lastReadAyat = ayat
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val binding = ItemSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SurahViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah = listSurah[position]
        holder.bind(surah)
    }

    override fun getItemCount(): Int = listSurah.size

    inner class SurahViewHolder(private val binding: ItemSurahBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(surah: Surah) {
            with(binding) {
                tvSurahNumber.text = surah.nomor.toString()
                tvSurahName.text = surah.nama
                tvSurahArab.text = surah.asma
                tvSurahTypeAyat.text =
                    itemView.context.getString(R.string.surah_type_ayat, surah.type, surah.jumlahAyat)

                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, SurahActivity::class.java)
////                    intent.putExtra(SurahActivity.EXTRA_SURAH, surah)
////                    intent.putExtra(SurahActivity.EXTRA_LASTREAD, lastReadAyat)
//                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}