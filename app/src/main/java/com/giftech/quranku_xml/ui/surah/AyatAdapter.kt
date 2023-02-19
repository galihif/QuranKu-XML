package com.giftech.quranku_xml.ui.surah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giftech.quranku_xml.R
import com.giftech.quranku_xml.data.model.Ayat
import com.giftech.quranku_xml.data.model.LastRead
import com.giftech.quranku_xml.databinding.ItemAyatBinding


class AyatAdapter(
    val onBookmarked:(LastRead) -> Unit
): RecyclerView.Adapter<AyatAdapter.AyatViewHolder>() {

    private var listAyat = ArrayList<Ayat>()
    private lateinit var lastReadAyat: LastRead

    fun setList(list: List<Ayat>){
        listAyat.clear()
        listAyat.addAll(list)
        notifyDataSetChanged()
    }

    fun setLastRead(lastReadAyatEntity: LastRead){
        this.lastReadAyat = lastReadAyatEntity
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        val binding = ItemAyatBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AyatViewHolder(binding,lastReadAyat)
    }

    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        val surah = listAyat[position]
        holder.bind(surah)
    }

    override fun getItemCount(): Int = listAyat.size

    inner class AyatViewHolder(
        private val binding: ItemAyatBinding,
        private val lastReadAyat:LastRead
        ): RecyclerView.ViewHolder(binding.root) {
        private var bookmarked = false

        fun bind(ayat: Ayat){

            bookmarked = isBookmarked(ayat)
            setBookmarkDrawable()

            with(binding){
                tvAyatNumber.text = ayat.nomor.toString()
                tvAyatArab.text = ayat.arab
                tvAyatArti.text = ayat.arti

                btnBookmark.setOnClickListener {
                    bookmarked = !bookmarked
                    if(bookmarked){
                        lastReadAyat.nomorSurah = ayat.nomorSurah
                        lastReadAyat.nomorAyat = ayat.nomor
                        setBookmarkDrawable()
                        onBookmarked(lastReadAyat)
                    }
                }
            }
        }

        private fun isBookmarked(ayat: Ayat): Boolean {
            if(ayat.nomorSurah==lastReadAyat.nomorSurah && ayat.nomor==lastReadAyat.nomorAyat ){
                return true
            }
            return false
        }

        private fun setBookmarkDrawable() {
            if(bookmarked){
                binding.btnBookmark.setImageResource(R.drawable.ic_bookmark)
            }else{
                binding.btnBookmark.setImageResource(R.drawable.ic_bookmark_border)
            }
        }
    }
}