package com.example.horosctest.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.horosctest.R
import com.example.horosctest.activities.adapters.CustomAdapter
import data.Horoscope

class MainActivity : AppCompatActivity() {

    val horosList = listOf<Horoscope>(
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

    lateinit var recViewMain: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initView()
    }
        private fun initView() {
            recViewMain = findViewById(R.id.recViewMain)
            recViewMain.adapter = CustomAdapter(horosList)

        }

}