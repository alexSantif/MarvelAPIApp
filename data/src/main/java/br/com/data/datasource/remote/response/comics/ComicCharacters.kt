package br.com.data.datasource.remote.response.comics

import com.google.gson.annotations.SerializedName

data class ComicCharacters(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ComicItems>,
    @SerializedName("returned")
    val returned: Int
)