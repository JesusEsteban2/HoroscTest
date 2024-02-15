package com.example.horosctest.activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.horosctest.R
import com.example.horosctest.activities.adapters.CustomAdapter
import data.Horoscope

class MainActivity : AppCompatActivity() {
    // Elementos LateInit
    lateinit var recViewMain: RecyclerView

    // Crear lista de elementos Horoscope
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Obtener la reciclerView
        recViewMain = findViewById(R.id.recViewMain)
        // Asignar el adapter a la reciclerView
        recViewMain.adapter = CustomAdapter(horosList,{posi ->
            onClickListener(posi)
        })
    }
    private fun onClickListener (p:Int){
        val intent:Intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("HOROS_NAME",getString(horosList[p].name))
            startActivity(intent)
    }
}