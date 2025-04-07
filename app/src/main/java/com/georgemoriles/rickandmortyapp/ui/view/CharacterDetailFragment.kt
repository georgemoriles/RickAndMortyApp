package com.georgemoriles.rickandmortyapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.georgemoriles.rickandmortyapp.databinding.FragmentCharacterDetailBinding


private var characterId: Int = 1

class CharacterDetailFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailBinding

    companion object {
        const val CHARACTER_ID = "character_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getInt(CHARACTER_ID)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

}