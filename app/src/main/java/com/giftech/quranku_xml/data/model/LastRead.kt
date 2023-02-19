package com.giftech.quranku_xml.data.model

import com.giftech.quranku_xml.data.source.local.entity.LastReadAyatEntity


data class LastRead(
    var nomorAyat:Int = 1,
    var nomorSurah:Int = 1,
    var namaSurah:String = "Al-Fatihah"
)

fun LastRead.toEntity(): LastReadAyatEntity =
    LastReadAyatEntity(
        nomorAyat = nomorAyat,
        nomorSurah = nomorSurah,
        namaSurah = namaSurah
    )
