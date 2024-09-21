package com.israelaguilar.videogamesrf.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.israelaguilar.videogamesrf.R
import com.israelaguilar.videogamesrf.data.GameRepository
import com.israelaguilar.videogamesrf.data.remote.RetrofitHelper
import com.israelaguilar.videogamesrf.data.remote.model.GameDto
import com.israelaguilar.videogamesrf.databinding.ActivityMainBinding
import com.israelaguilar.videogamesrf.ui.fragments.GamesListFragment
import com.israelaguilar.videogamesrf.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    /*private lateinit var repository: GameRepository
    private lateinit var retrofit: Retrofit*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Mostramos el fragment inicial GamesListFragment
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, GamesListFragment())
                .commit()
        }

        /*
        // Obteniendo la instancia de retrofit
        retrofit = RetrofitHelper().getRetrofit()

        // Obteniendo el repositorio
        repository = GameRepository(retrofit)
        */
    }

    /*fun click(view: View){
        val call: Call<MutableList<GameDto>> = repository.getGames("cm/games/games_list.php")

        call.enqueue(object: Callback<MutableList<GameDto>>{
            override fun onResponse(
                call: Call<MutableList<GameDto>>,
                response: Response<MutableList<GameDto>>
            ) {
                Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.body()}")

            }

            override fun onFailure(p0: Call<MutableList<GameDto>>, p1: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Error: No hay conexión disponible",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }*/
}