package br.com.alex.marvelapiapp.data.datasource.remote.response.characters


import com.google.gson.annotations.SerializedName

data class CharacterSeries(
    @SerializedName("available")
    val available: Int? = null,
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("returned")
    val returned: Int? = null
)