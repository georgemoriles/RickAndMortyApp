package com.georgemoriles.rickandmortyapp.data.network

import com.georgemoriles.rickandmortyapp.data.model.CharacterItem
import com.georgemoriles.rickandmortyapp.data.model.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiClient {


    @GET("character")
    suspend fun getCharacterList(
        @Query("page") page: Int
    ): Response<CharacterList>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterItem


}