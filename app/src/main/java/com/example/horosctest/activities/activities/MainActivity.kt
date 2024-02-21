package com.example.horosctest.activities.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.horosctest.R
import com.example.horosctest.activities.adapters.CustomAdapter
import data.horosList

class MainActivity : AppCompatActivity() {
    // Elementos LateInit
    lateinit var recViewMain: RecyclerView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Obtener la reciclerView
        recViewMain = findViewById(R.id.recViewMain)

        // Asignar el adapter a la reciclerView
        recViewMain.adapter = CustomAdapter(data.horosList) { posi ->
            onClickListener(posi)
        }
    }

    //Función OnClick a pasar al adapter.
    @SuppressLint("SuspiciousIndentation")
    private fun onClickListener (p:Int){
        // Intent para cambio de ventana a DetailActivity.
        val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("HOROS_NAME",getString(data.horosList[p].name))
            startActivity(intent)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        //Incorpora el menu a activity_detail
        menuInflater.inflate(R.menu.horoscope_menu,menu)

        initSearchView(menu?.findItem(R.id.menu_search))

        // completa la acción en super
        return super.onCreateOptionsMenu(menu)

    }
    private fun initSearchView(searchItem: MenuItem?) {
        var horoscopeList = horosList
        if (searchItem != null) {
            var searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    if (query.isNullOrEmpty()) {
                        horoscopeList = horosList
                    } else {
                        horoscopeList = horosList
                            .filter {getString(it.name).contains(query, true) }
                    }
                    return true
                }
            })
        }
    }
}