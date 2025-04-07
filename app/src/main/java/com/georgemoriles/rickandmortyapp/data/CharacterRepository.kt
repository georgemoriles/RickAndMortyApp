package com.georgemoriles.rickandmortyapp.data

import com.georgemoriles.rickandmortyapp.data.model.CharacterItem
import com.georgemoriles.rickandmortyapp.data.network.CharacterService

class CharacterRepository {

    private val api = CharacterService()

    suspend fun getCharacterList(id: Int): List<CharacterItem>{
        val response = api.getCharacters(id)
        return response.results
    }

    suspend fun getCharacter(id: Int): CharacterItem? {
        return api.getCharacter(id)
    }

}