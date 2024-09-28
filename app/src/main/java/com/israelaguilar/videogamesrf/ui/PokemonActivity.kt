package com.israelaguilar.videogamesrf.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.israelaguilar.videogamesrf.R
import com.israelaguilar.videogamesrf.data.remote.PokemonApi
import com.israelaguilar.videogamesrf.data.remote.model.PokemonDetailDto
import com.israelaguilar.videogamesrf.databinding.ActivityPokemonBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class PokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonBinding

    companion object{
        const val BASE_URL = "https://pokeapi.co/"
        const val LOGTAG = "LOGSPOKEMON"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Log.d(LOGTAG,"Hola")

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokemonApi = retrofit.create(PokemonApi::class.java)

        val call: Call<PokemonDetailDto> = pokemonApi.getPokemonDetail("132")

        call.enqueue(object: Callback<PokemonDetailDto>{
            override fun onResponse(p0: Call<PokemonDetailDto>, response: Response<PokemonDetailDto>) {
                Log.d(
                    LOGTAG,
                    "URL del pokémon: ${response.body()?.sprites?.other?.officialArtwork?.frontDefault}"
                )
                Log.d(
                    LOGTAG,
                    "URL del pokémon: ${response.body()?.sprites?.other?.officialArtwork?.frontShiny}"
                )

                // Poner la primera letra en mayúscula
                binding.tvPokemon.text = response.body()?.name?.replaceFirstChar { firstChar ->
                    firstChar.uppercase()
                }

                // Es lo mismo para poner la primera letra en mayúscula pero de distinta manera
                //binding.tvPokemon.text = response.body()?.name?.capitalize()

                // Ejemplo de uso de API
                // response.body()?.game_indices?.get(5)?.version?.name


                Picasso.get()
                    .load(response.body()?.sprites?.other?.officialArtwork?.frontDefault)
                    .into(binding.ivPokemon)

                /*Glide.with(this@PokemonActivity)
                    .load(response.body()?.sprites?.other?.officialArtwork?.frontDefault)
                    .into(binding.ivPokemon)*/
            }

            override fun onFailure(p0: Call<PokemonDetailDto>, p1: Throwable) {

            }



        })
    }
}