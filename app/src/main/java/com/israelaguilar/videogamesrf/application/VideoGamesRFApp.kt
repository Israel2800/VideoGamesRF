package com.israelaguilar.videogamesrf.application

import android.app.Application
import com.israelaguilar.videogamesrf.data.GameRepository
import com.israelaguilar.videogamesrf.data.remote.RetrofitHelper
// import retrofit2.Retrofit

class VideoGamesRFApp: Application() {

    private val retrofit by lazy{
        RetrofitHelper().getRetrofit()
    }

    val repository by lazy {
        GameRepository(retrofit)
    }

}