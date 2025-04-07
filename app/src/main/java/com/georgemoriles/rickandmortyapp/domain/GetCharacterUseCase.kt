package com.georgemoriles.rickandmortyapp.domain

import com.georgemoriles.rickandmortyapp.data.CharacterRepository
import com.georgemoriles.rickandmortyapp.data.model.CharacterItem

class GetCharacterUseCase {

    private val repository = CharacterRepository()

    suspend operator fun invoke(id: Int):List<CharacterItem> = repository.getCharacterList(id)



}