package br.com.alex.marvelapiapp.data.datasource.remote.response.characters


import com.google.gson.annotations.SerializedName

data class CharacterThumbnail(
    @SerializedName("extension")
    val extension: String? = null,
    @SerializedName("path")
    val path: String? = null
)