package com.giftech.quranku_xml.data.source.local.entity

import com.giftech.quranku_xml.data.model.LastRead

class LastReadAyatEntity(
    var nomorAyat: Int = 0,
    var nomorSurah: Int = 0,
    var namaSurah: String = ""
)

fun LastReadAyatEntity.toModel(): LastRead =
    LastRead(
        nomorAyat = nomorAyat,
        nomorSurah = nomorSurah,
        namaSurah = namaSurah
    )