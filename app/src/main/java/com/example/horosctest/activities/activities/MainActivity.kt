package com.example.horosctest.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.horosctest.R
import data.Horoscope

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val horosList= listOf<Horoscope>(
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
            Horoscope.libra,
        )

        initView()
    }
    private fun initView() {

    }
}