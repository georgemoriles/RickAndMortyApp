package com.georgemoriles.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class CharacterList(
    @SerializedName("info") val info: CharacterInfo,
    @SerializedName("results") val results: List<CharacterItem>
)
