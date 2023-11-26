package com.example.perfilfalso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var ImageView : ImageView
    private lateinit var textpais : TextView
    private lateinit var textgender : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textViewName)
        ImageView = findViewById(R.id.imageViewFoto)
        textpais = findViewById(R.id.Pais)
        textgender = findViewById(R.id.gender)

        getPerfil()

    }

    private fun getPerfil() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getPerfiles("api/")
            val response = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    response?.let { randomUserResponse ->
                        val user = randomUserResponse.results.firstOrNull()
                        user?.let {
                            val fullName = "${it.name.first} ${it.name.last}"
                            val country = "${it.location.country}"
                            val gender = "${it.gender}"
                            textView.text = "Perfil de " + fullName
                            textpais.text = "Pais: " + country
                            textgender.text = "Genero: " + gender

                            Picasso.get().load(it.picture.large).into(ImageView)
                        }

                    }
                }
            }
        }
    }


    // conexion de una api con retrofit
    private fun getRetrofit(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // variables estaticas de una clase
    companion object{
        const val BASE_URL = "https://randomuser.me/"
    }
}




