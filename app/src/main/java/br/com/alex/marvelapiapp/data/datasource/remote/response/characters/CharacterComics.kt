package br.com.alex.marvelapiapp.data.datasource.remote.response.characters


import com.google.gson.annotations.SerializedName

data class CharacterComics(
    @SerializedName("available")
    val available: Int? = null,
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("items")
    val items: List<CharacterComicItem>? = null,
    @SerializedName("returned")
    val returned: Int? = null
)