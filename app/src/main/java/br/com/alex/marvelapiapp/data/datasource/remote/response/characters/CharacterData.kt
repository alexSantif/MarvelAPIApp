package br.com.alex.marvelapiapp.data.datasource.remote.response.characters


import com.google.gson.annotations.SerializedName

data class  CharacterData(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("limit")
    val limit: Int? = null,
    @SerializedName("offset")
    val offset: Int? = null,
    @SerializedName("results")
    val characterResults: List<CharacterResult>? = null,
    @SerializedName("total")
    val total: Int? = null
)