package com.israelaguilar.videogamesrf.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.israelaguilar.videogamesrf.data.remote.model.GameDto
import com.israelaguilar.videogamesrf.databinding.GameElementBinding
import com.squareup.picasso.Picasso

class GameViewHolder(
    private val binding: GameElementBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(game: GameDto){

        binding.tvTitle.text = game.title

        // Con glide

        // Viewgolder: (binding.root.context)
        // Fragment: requireContext
        // Activity: this
        Glide.with(binding.root.context)
            .load(game.thumbnail)
            .into(binding.ivThumbnail)

        // Con Picasso
        /* Picasso.get()
            .load(game.thumbnail)
            .into(binding.ivThumbnail)*/

    }
}