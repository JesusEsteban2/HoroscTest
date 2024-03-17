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
import data.Horoscope
import data.HoroscopeList

class MainActivity : AppCompatActivity() {
    // Elementos LateInit
    lateinit var recViewMain: RecyclerView
    lateinit var adapter:CustomAdapter

    var horosList = HoroscopeList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Obtener la reciclerView
        recViewMain = findViewById(R.id.recViewMain)

        adapter = CustomAdapter(horosList.lista) { posi ->
            onClickListener(posi)
        }
        // Asignar el adapter a la reciclerView
        recViewMain.adapter = adapter
    }

    //Función OnClick a pasar al adapter.
    @SuppressLint("SuspiciousIndentation")
    private fun onClickListener (p:Int){
        // Intent para cambio de ventana a DetailActivity.
        val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("HOROS_IND",p)
            startActivity(intent)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        //Incorpora el menu a activity_detail
        menuInflater.inflate(R.menu.main_menu,menu)

        initSearchView(menu?.findItem(R.id.menu_search))

        // completa la acción en super
        return super.onCreateOptionsMenu(menu)

    }
    private fun initSearchView(searchItem: MenuItem?) {
        if (searchItem == null) {
            return
        } else {

            var searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    var newList:List<Horoscope>
                    if (query.isNullOrEmpty()) {
                        newList = horosList.lista
                    } else {
                        newList = horosList.filterListByName(query, searchView.context)
                    }
                    adapter.updateList(newList)
                    return true
                }
            })
        }

    }

    companion object{
        val horoList:HoroscopeList = HoroscopeList()
    }
}