package br.com.data.datasource.remote.response.comics

import com.google.gson.annotations.SerializedName

data class ComicsResponse(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("copyright")
    val copyright: String? = null,
    @SerializedName("attributionText")
    val attributionText: String? = null,
    @SerializedName("attributionHTML")
    val attributionHTML: String? = null,
    @SerializedName("etag")
    val etag: String? = null,
    @SerializedName("data")
    val data: ComicData? = null
)