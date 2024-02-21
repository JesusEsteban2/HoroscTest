package data

import com.example.horosctest.R

sealed class Horoscope (val img:Int, val name:Int, val dates:Int){
    object aries:Horoscope(R.drawable.aries,R.string.aries,R.string.datesAries)
    object pisces:Horoscope(R.drawable.pisces, R.string.pisces,R.string.datesPisces)
    object aquarius:Horoscope(R.drawable.aquario, R.string.aquarius,R.string.datesAquarius)
    object capricorn:Horoscope(R.drawable.capricorn, R.string.capricorn,R.string.datesCapricorn)
    object sagitarius:Horoscope(R.mipmap.sagitarius, R.string.sagitarius,R.string.datesSagitarius)
    object libra:Horoscope(R.mipmap.libra, R.string.libra,R.string.datesLibra)
    object virgo:Horoscope(R.mipmap.virgo, R.string.virgo,R.string.datesVirgo)
    object leo:Horoscope(R.mipmap.leo, R.string.leo,R.string.datesLeo)
    object cancer:Horoscope(R.drawable.cancer, R.string.cancer,R.string.datesCancer)
    object gemini:Horoscope(R.mipmap.gemini, R.string.gemini,R.string.datesGemini)
    object taurus:Horoscope(R.mipmap.taurus, R.string.taurus,R.string.datesTaurus)
    object scorpio:Horoscope(R.drawable.scorpio, R.string.scorpio,R.string.datesScorpio)
}

// Crear lista de elementos Horoscope
val horosList = listOf(
    Horoscope.aries,
    Horoscope.pisces,
    Horoscope.aquarius,
    Horoscope.capricorn,
    Horoscope.sagitarius,
    Horoscope.libra,
    Horoscope.virgo,
    Horoscope.leo,
    Horoscope.cancer,
    Horoscope.gemini,
    Horoscope.taurus,
    Horoscope.scorpio
)