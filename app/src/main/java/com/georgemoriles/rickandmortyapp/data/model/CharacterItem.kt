package com.georgemoriles.rickandmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class CharacterItem(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("location") val location: CharacterLocation,
    @SerializedName("origin") val origin: CharacterOrigin,
    @SerializedName("episode") val episode: List<String>,
    @SerializedName("image") val image: String
)
