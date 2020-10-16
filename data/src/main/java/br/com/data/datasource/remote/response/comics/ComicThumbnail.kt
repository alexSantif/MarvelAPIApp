package br.com.data.datasource.remote.response.comics

import com.google.gson.annotations.SerializedName

data class ComicThumbnail(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
)