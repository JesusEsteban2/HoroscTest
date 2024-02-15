package data

sealed class Horoscope (val img:Int, val name:Int){
    object aries:Horoscope(com.example.horosctest.R.drawable.aries,com.example.horosctest.R.string.aries)
    object pisces:Horoscope(com.example.horosctest.R.drawable.pisces,com.example.horosctest.R.string.pisces)
    object aquarius:Horoscope(com.example.horosctest.R.drawable.aquario,com.example.horosctest.R.string.aquarius)
    object capricorn:Horoscope(com.example.horosctest.R.drawable.capricorn,com.example.horosctest.R.string.capricorn)
    object sagitarius:Horoscope(com.example.horosctest.R.mipmap.sagitarius,com.example.horosctest.R.string.sagitarius)
    object libra:Horoscope(com.example.horosctest.R.mipmap.libra,com.example.horosctest.R.string.libra)
    object virgo:Horoscope(com.example.horosctest.R.mipmap.virgo,com.example.horosctest.R.string.virgo)
    object leo:Horoscope(com.example.horosctest.R.mipmap.leo,com.example.horosctest.R.string.leo)
    object cancer:Horoscope(com.example.horosctest.R.drawable.cancer,com.example.horosctest.R.string.cancer)
    object gemini:Horoscope(com.example.horosctest.R.mipmap.gemini,com.example.horosctest.R.string.gemini)
    object taurus:Horoscope(com.example.horosctest.R.mipmap.taurus,com.example.horosctest.R.string.taurus)
    object scorpio:Horoscope(com.example.horosctest.R.drawable.scorpio,com.example.horosctest.R.string.scorpio)
}