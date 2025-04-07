package com.georgemoriles.rickandmortyapp.ui.adapter


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.georgemoriles.rickandmortyapp.data.model.CharacterItem
import com.georgemoriles.rickandmortyapp.databinding.ItemCharactersBinding

class CharactersViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val binding = ItemCharactersBinding.bind(view)

    fun bind(characterItemResponse: CharacterItem, onItemSelected: (Int) -> Unit) {
        binding.tvCharacterName.text = characterItemResponse.name
        Glide.with(binding.ivCharacter.context).load(characterItemResponse.image).into(binding.ivCharacter)
        binding.root.setOnClickListener { onItemSelected(characterItemResponse.id) }
    }


}