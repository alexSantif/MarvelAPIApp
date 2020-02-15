package br.com.alex.marvelapiapp.data.datasource.remote.response.comics

import com.google.gson.annotations.SerializedName

data class ComicPrices(
    @SerializedName("type")
    val type: String,
    @SerializedName("price")
    val price: Double
)