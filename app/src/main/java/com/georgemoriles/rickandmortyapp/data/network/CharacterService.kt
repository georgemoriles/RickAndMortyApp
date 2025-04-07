package com.georgemoriles.rickandmortyapp.data.network

import com.georgemoriles.rickandmortyapp.core.RetrofitHelper
import com.georgemoriles.rickandmortyapp.data.model.CharacterItem
import com.georgemoriles.rickandmortyapp.data.model.CharacterList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CharacterService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCharacters(id: Int): CharacterList {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CharacterApiClient::class.java).getCharacterList(id)
            (response.body() ?: "") as CharacterList
        }
    }

    suspend fun getCharacter(id: Int): CharacterItem? {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CharacterApiClient::class.java).getCharacter(id)
            response
        }
    }
}