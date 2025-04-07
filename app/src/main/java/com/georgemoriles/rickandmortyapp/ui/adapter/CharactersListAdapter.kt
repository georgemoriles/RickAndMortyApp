package com.georgemoriles.rickandmortyapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.georgemoriles.rickandmortyapp.R
import com.georgemoriles.rickandmortyapp.data.model.CharacterItem

class CharactersListAdapter (
    var characterList: List<CharacterItem> = emptyList(),
    private val onItemSelected: (Int) -> Unit
):
    RecyclerView.Adapter<CharactersViewHolder>() {
    private var characterAddList: MutableList<CharacterItem> = mutableListOf()

    fun updateList(list: List<CharacterItem>) {
        characterList = list
        characterAddList = list.toMutableList()
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<CharacterItem>) {
        val startPosition = characterList.size
        characterAddList.addAll(newItems)
        notifyItemRangeInserted(startPosition, newItems.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_characters, parent, false)
        )
    }

    override fun onBindViewHolder(viewholder: CharactersViewHolder, position: Int) {
        viewholder.bind(characterList[position],onItemSelected)
    }

    override fun getItemCount() = characterList.size
}