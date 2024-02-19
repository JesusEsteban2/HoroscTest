package com.example.horosctest.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.horosctest.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DetailActivity : AppCompatActivity() {
    // Lateinit
    lateinit var textTitle: TextView
    lateinit var textToday:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        textTitle = findViewById(R.id.textTit)
        textToday = findViewById(R.id.textToday)

        textTitle.text = intent.getStringExtra("HOROS_NAME")


        // Llamada en 2º Plano
        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano
            val result = horoRepo().creaURL(textTitle.text.toString())
            // Modificar UI
            runOnUiThread {
            textToday.text = result
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
        val jsonBody=JSONObject()
        var result="Algo"

        // Crear la conexión HTTP
        try {
            connection = (url.openConnection() as? HttpsURLConnection)!!
            connection.requestMethod = "GET"                            // Establecer el método GET
            connection.setRequestProperty("Accept", "application/json") // Establecer el tipo de contenido
            connection.doInput = true
            connection.inputStream.read(jsonBody. as ByteArray)
            result = jsonBody.getJSONObject("data").getString("horoscope_data").toString()
        } catch (e: Exception)
        {
            Log.e("HTTP", "Error en la solicitud. ", e)
        } finally
        {
            // Cerrar la conexión
            connection?.disconnect()

        }
        return result
    }
}