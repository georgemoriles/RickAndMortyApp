package com.georgemoriles.rickandmortyapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.georgemoriles.rickandmortyapp.data.model.CharacterItem
import com.georgemoriles.rickandmortyapp.databinding.ActivityCharacterDetailBinding
import com.georgemoriles.rickandmortyapp.domain.GetCharacterInfoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivityCharacterDetailBinding

    companion object {
        const val CHARACTER_ID = "character_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: Int = intent.getIntExtra(CHARACTER_ID, 0)
        getCharacterId(id)

    }


    private fun getCharacterId(id: Int) {

        CoroutineScope(Dispatchers.IO).launch {
            val result = GetCharacterInfoUseCase().invoke(id)

            //Log.d("jorge", "tamano" + result.toString())
            if (result != null) {
                    Log.i("JORGE", result.toString())
                    runOnUiThread {
                        createUI(result)
                    }
            }

        }

    }

    private fun createUI(characterDetail: CharacterItem?) {
        Glide.with(binding.imgCharacter.context).load(characterDetail!!.image).into(binding.imgCharacter)
        binding.tvCharacterName.text = characterDetail.name
        binding.tvGender.text = characterDetail.gender
        binding.tvStatus.text = characterDetail.status
        binding.tvLocation.text = characterDetail.location.name
        binding.tvSpecies.text = characterDetail.species
        binding.tvEpisodes.text = characterDetail.episode.size.toString()
    }
}