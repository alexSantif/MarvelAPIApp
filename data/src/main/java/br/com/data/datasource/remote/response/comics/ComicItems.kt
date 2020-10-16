package br.com.data.datasource.remote.response.comics

import com.google.gson.annotations.SerializedName

data class ComicItems(
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)