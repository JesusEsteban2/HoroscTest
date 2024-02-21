package com.example.horosctest.activities.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.horosctest.R
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
    lateinit var horoToday:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        textTitle = findViewById(R.id.textTit)
        horoToday = findViewById(R.id.textToday)





        textTitle.text = intent.getStringExtra("HOROS_NAME")

        // activa visualización de la fecha Home.
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Establece titulo y subtitulo de la vista.
        this.supportActionBar?.title = textTitle.text.toString()
        this.supportActionBar?.subtitle="Today Horoscope"

        // Llamada en 2º Plano para el contenido obtenido de internet
        CoroutineScope(Dispatchers.IO).launch {

            val result = horoRepo().creaURL(textTitle.text.toString())
            // Modificar UI
            runOnUiThread {
            horoToday.text = result
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
        }
        return super.onOptionsItemSelected(item)
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
        var line: String?

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