package com.example.horosctest.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.horosctest.R
import com.example.horosctest.activities.adapters.CustomAdapter
import data.Horoscope

class MainActivity : AppCompatActivity() {
    val recViewMain: RecyclerView =findViewById(R.id.recViewMain)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val horosList = listOf<Horoscope>(
            Horoscope.aries,
            Horoscope.pisces,
            Horoscope.aquarius,
            Horoscope.capricorn,
            Horoscope.sagitarius,
            Horoscope.libra,
            Horoscope.aries,
            Horoscope.pisces,
            Horoscope.aquarius,
            Horoscope.capricorn,
            Horoscope.sagitarius,
            Horoscope.libra
        )


        initView(horosList)
    }
        private fun initView(horosList:List<Horoscope>) {

            recViewMain.adapter = CustomAdapter(horosList)

        }

}