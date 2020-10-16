package br.com.data.datasource.remote.response.comics

import com.google.gson.annotations.SerializedName

data class ComicDates(
    @SerializedName("type")
    val type: String,
    @SerializedName("date")
    val date: String
)