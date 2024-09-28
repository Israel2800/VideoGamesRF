package com.israelaguilar.videogamesrf.data

import com.israelaguilar.videogamesrf.data.remote.GamesApi
import com.israelaguilar.videogamesrf.data.remote.model.GameDetailDto
import com.israelaguilar.videogamesrf.data.remote.model.GameDto
import retrofit2.Call
import retrofit2.Retrofit

class GameRepository(
    private val retrofit: Retrofit
) {

    private val gamesApi: GamesApi = retrofit.create(GamesApi::class.java)

    /*fun getGames(url: String?): Call<MutableList<GameDto>> =
        gamesApi.getGames(url)

    fun getGameDetail(id: String?): Call<GameDetailDto> =
        gamesApi.getGameDetail(id)*/


    // Para Apiary
    // Obtener la lista de juegos
    fun getGames(): Call<MutableList<GameDto>> = gamesApi.getGames()

    // Obtener detalles de un juego
    fun getGameDetail(id: String): Call<GameDetailDto> = gamesApi.getGameDetail(id)
}