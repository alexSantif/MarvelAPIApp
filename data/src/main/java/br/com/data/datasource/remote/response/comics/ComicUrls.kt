package br.com.data.datasource.remote.response.comics

import com.google.gson.annotations.SerializedName

data class ComicUrls(
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)