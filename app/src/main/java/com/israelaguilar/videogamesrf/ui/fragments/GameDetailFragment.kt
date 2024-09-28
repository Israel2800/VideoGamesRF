package com.israelaguilar.videogamesrf.ui.fragments

import android.graphics.text.LineBreaker
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.israelaguilar.videogamesrf.R
import com.israelaguilar.videogamesrf.application.VideoGamesRFApp
import com.israelaguilar.videogamesrf.data.GameRepository
import com.israelaguilar.videogamesrf.data.remote.model.GameDetailDto
import com.israelaguilar.videogamesrf.data.remote.model.GameDto
import com.israelaguilar.videogamesrf.databinding.FragmentGameDetailBinding
import com.israelaguilar.videogamesrf.utils.Constants
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val GAME_ID = "game_id"

class GameDetailFragment : Fragment() {

    private var gameId: String? = null

    private var _binding: FragmentGameDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            gameId = args.getString(GAME_ID)

            Log.d(Constants.LOGTAG, "Id recibido $gameId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Se manda a llamar ya cuando el fragment es visible en pantalla
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obteniendo la instancia al repositorio
        repository = (requireActivity().application as VideoGamesRFApp).repository

        gameId?.let { id ->
            // Hago la llamada al endpoint para consumir los detalles del juego

            val call: Call<GameDetailDto> = repository.getGameDetail(id)

            call.enqueue(object: Callback<GameDetailDto>{
                override fun onResponse(p0: Call<GameDetailDto>, response: Response<GameDetailDto>) {

                    binding.pbLoading.visibility = View.GONE

                    // Aquí utilizamos la respuesta exitosa y asignamos los valores a las vistas
                    binding.tvTitle.text = response.body()?.title

                    /*Glide.with(requireActivity())
                        .load(response.body()?.image)
                        .into(binding.ivImage)*/

                    Picasso.get()
                        .load(response.body()?.image)
                        .into(binding.ivImage)

                    binding.tvLongDesc.text = response.body()?.longDesc

                    // Para justificar el texto de un textview
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) // Q corresponde a Android 10
                        binding.tvLongDesc.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
                }

                override fun onFailure(p0: Call<GameDetailDto>, p1: Throwable) {
                    // Manejo del error de conexión
                }

            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(gameId: String) =
            GameDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(GAME_ID, gameId)
                }
            }
    }
}