package br.com.data.datasource.remote.response.characters

import com.google.gson.annotations.SerializedName

data class CharacterComicItem(
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)