package com.georgemoriles.rickandmortyapp.domain

import com.georgemoriles.rickandmortyapp.data.CharacterRepository
import com.georgemoriles.rickandmortyapp.data.model.CharacterItem

class GetCharacterInfoUseCase {

    private val repository = CharacterRepository()

    suspend fun invoke(id: Int): CharacterItem? = repository.getCharacter(id)
}