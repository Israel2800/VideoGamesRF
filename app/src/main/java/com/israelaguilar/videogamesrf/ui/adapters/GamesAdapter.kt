package com.israelaguilar.videogamesrf.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.israelaguilar.videogamesrf.data.remote.model.GameDto
import com.israelaguilar.videogamesrf.databinding.GameElementBinding

class GamesAdapter(
    private val games: MutableList<GameDto>,
    private val onGameClicked: (GameDto) -> Unit
): RecyclerView.Adapter<GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = GameElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun getItemCount(): Int = games.size


    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {

        val game = games[position]

        holder.bind(game)

        holder.itemView.setOnClickListener {
            // Para los click a los juegos
            onGameClicked(game)
        }

    }

}