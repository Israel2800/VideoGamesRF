package com.israelaguilar.videogamesrf.data.remote

import com.israelaguilar.videogamesrf.data.remote.model.GameDetailDto
import com.israelaguilar.videogamesrf.data.remote.model.GameDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GamesApi {

    // Aquí definimos los endpoints
/*
    // cm/games/games_list.php
    @GET
    fun getGames(
        @Url url: String?
    ): Call<MutableList<GameDto>>
    // Se mandaría a llamar así: getGames("cm/games/games_list.php")

    @GET("cm/games/games_list.php")
    fun getGames(): Call<MutableList<GameDto>>
    // Se mandaría a llamar así: getGames()


    // cm/games/game_detail.php?id=21357
    @GET("cm/games/game_detail.php?id=21357")
    fun getGameDetail(
        @Query("id") id: String?/*,
        @Query("name") name: String?*/
    ): Call<GameDetailDto>
    // Se mandaría a llamar así: getGameDetail("21357")

    // cm/games/21357/israel
    @GET("cm/games/{id}/{name}")
    fun getGameTest(
        @Path("id") id: String?,
        @Path("name") name: String?
    ): Call<GameDetailDto>
    // Se mandaría a llamar así: getGameTest("21357", "Israel")
*/

    // Para Apiary

    // Obtener la lista de juegos
    @GET("games/games_list")
    fun getGames(): Call<MutableList<GameDto>>

    // Obtener un juego en específico
    @GET("games/game_detail/{id}")
    fun getGameDetail(
        @Path("id") id: String?
    ): Call<GameDetailDto>

}