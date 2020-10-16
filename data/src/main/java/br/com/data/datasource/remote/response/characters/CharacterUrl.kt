package br.com.data.datasource.remote.response.characters


import com.google.gson.annotations.SerializedName

data class CharacterUrl(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("url")
    val url: String? = null
)