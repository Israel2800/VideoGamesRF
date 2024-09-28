package com.israelaguilar.videogamesrf.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.israelaguilar.videogamesrf.R
import com.israelaguilar.videogamesrf.application.VideoGamesRFApp
import com.israelaguilar.videogamesrf.data.GameRepository
import com.israelaguilar.videogamesrf.data.remote.model.GameDto
import com.israelaguilar.videogamesrf.databinding.FragmentGamesListBinding
import com.israelaguilar.videogamesrf.ui.adapters.GamesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GamesListFragment : Fragment() {

    private var _binding: FragmentGamesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGamesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtenemos la instancia al repositorio
        repository = (requireActivity().application as VideoGamesRFApp).repository

        val call: Call<MutableList<GameDto>> = repository.getGames()

        call.enqueue(object: Callback<MutableList<GameDto>>{
            override fun onResponse(
                p0: Call<MutableList<GameDto>>,
                response: Response<MutableList<GameDto>>
            ) {
                binding.pbLoading.visibility = View.GONE

                response.body()?.let{ games ->
                    // Les pasamos los juegos al recycler view y lo instanciamos
                    binding.rvGames.apply {
                        layoutManager = LinearLayoutManager(requireContext())

                        // Con un grid layout
                        //layoutManager = GridLayoutManager(requireContext(), 4)

                        adapter = GamesAdapter(games){ game ->
                            // Aquí realizamos la acción para ir a ver los detalles del juego
                            game.id?.let { id ->
                                requireActivity().supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, GameDetailFragment.newInstance(id))
                                    .addToBackStack(null)
                                    .commit()
                            }

                        }
                    }
                }
            }

            override fun onFailure(p0: Call<MutableList<GameDto>>, p1: Throwable) {
                Toast.makeText(
                    requireContext(),
                    "Error no hay conexión disponible",
                    Toast.LENGTH_SHORT
                ).show()
            }


        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}