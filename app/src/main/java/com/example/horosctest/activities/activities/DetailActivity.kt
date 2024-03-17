package com.example.horosctest.activities.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.example.horosctest.R
import data.HoroscopeList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DetailActivity : AppCompatActivity() {

    // Lateinit
    lateinit var textTitle: TextView
    lateinit var imageTitle:ImageView
    lateinit var horoToday:TextView
    lateinit var favImage:ImageView

    var horosList=MainActivity.horoList
    var ind:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        textTitle = findViewById(R.id.textTit)
        imageTitle= findViewById(R.id.hImage)
        horoToday = findViewById(R.id.textToday)
        favImage= findViewById(R.id.favImage)

        //Recibe el indice de la lista para identificar el signo
        ind = intent.getIntExtra("HOROS_IND",0)

        // Establece el signo a mostrar
        // Todo falta mostrar la imagen


        // activa visualización de la flecha Home en el menú.
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Mostrar el signo del indice pasado
        showSign(ind)

        favImage.setOnClickListener {
            if (horosList.fav!=ind) {
                horosList.fav = ind
                showSign(ind)
            }
        }

    }
    // creación del menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        //Incorpora el menu a activity_detail
        menuInflater.inflate(R.menu.horoscope_menu,menu)

        // completa la acción en super
        return super.onCreateOptionsMenu(menu)

    }

    // Volver a la pantalla anterior al pulsar la fecha.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.backItem -> {
                if (ind>0) {
                    ind=ind-1
                    showSign(ind)
                    return true
                }
            }
            R.id.nextItem -> {
                if (ind<horosList.lista.size-1) {
                    ind++
                    showSign(ind)
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Función para visualizar el horoscopo seleccionado.
     *
     * @param ind: indice del elemento seleccionado
     * @param c: Contexto de uso.
     */
    fun showSign(ind:Int) {

        // Establece titulo y subtitulo de la vista.
        this.supportActionBar?.title = getString(horosList.lista[ind].name)
        this.supportActionBar?.subtitle = getString(horosList.lista[ind].dates)

        imageTitle.setImageBitmap(getDrawable(horosList.lista[ind].img)
            ?.toBitmap() ?: getDrawable(R.drawable.aries)!!.toBitmap() )
        if (horosList.fav==ind) {
            favImage.setImageDrawable(getDrawable(R.drawable.heart_favorite))
        }else {
            favImage.setImageDrawable(getDrawable(R.drawable.heart_svg))
        }

        textTitle.text = getString(horosList.lista[ind].name)
        imageTitle
        // Llamada en 2º Plano para el contenido obtenido de internet
        CoroutineScope(Dispatchers.IO).launch {

            val result = horoRepo().creaURL(getString(horosList.lista[ind].name))
            // Modificar UI
            runOnUiThread {
                horoToday.text = result
            }
        }
    }
}


class horoRepo() {

    fun creaURL(horo: String): String {
        lateinit var connection: HttpsURLConnection
        // Establece la URL
        val url =
            URL("https://horoscope-app-api.vercel.app/api/v1/get-horoscope/daily?sign=$horo&day=TODAY")
        var result="Data could not be recovered"
        var jsonBody= JSONObject()
        // Crear la conexión HTTP
        try {
            connection = (url.openConnection() as? HttpsURLConnection)!!
            connection.requestMethod = "GET"                            // Establecer el método GET
            connection.setRequestProperty("Accept", "application/json") // Establecer el tipo de contenido

            val responseCode = connection.responseCode
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                val response = readStream (connection.inputStream)
                Log.i("HTTP", "Respuesta: ${response.toString()}")
                jsonBody=JSONObject(response.toString())
                result = jsonBody.getJSONObject("data").getString("horoscope_data").toString()
            } else {
                Log.i("HTTP", "Error en la solicitud. Código de respuesta: $responseCode")
            }
        } catch (e: Exception) {
            Log.e("HTTP", "Error en la solicitud. ", e)
        } finally {
            // Cerrar la conexión
            connection?.disconnect()
        }
        return result
    }
    private fun readStream (inputStream: InputStream) : StringBuilder {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val response = StringBuilder()

        // while (.also { line = it } != null) {
        //    response.append(line)
        // }

        for (line in reader.readLine()){
            response.append(line)
        }
        reader.close()
        return response
    }
}