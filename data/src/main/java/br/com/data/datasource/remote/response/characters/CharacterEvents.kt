package br.com.data.datasource.remote.response.characters


import com.google.gson.annotations.SerializedName

data class CharacterEvents(
    @SerializedName("available")
    val available: Int? = null,
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("returned")
    val returned: Int? = null
)