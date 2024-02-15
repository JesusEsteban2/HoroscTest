package com.example.horosctest.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.horosctest.R

class DetailActivity : AppCompatActivity() {
    // Lateinit
    lateinit var texto: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        texto=findViewById(R.id.text1)
        texto.text=intent.getStringExtra("HOROS_NAME")
    }
}