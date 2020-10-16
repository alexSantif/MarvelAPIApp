package br.com.data.datasource.remote.response.comics

import com.google.gson.annotations.SerializedName

data class ComicResults(
    @SerializedName("id")
    val id: Int,
    @SerializedName("digitalId")
    val digitalId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("issueNumber")
    val issueNumber: Int,
    @SerializedName("variantDescription")
    val variantDescription: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("upc")
    val upc: String,
    @SerializedName("diamondCode")
    val diamondCode: String,
    @SerializedName("ean")
    val ean: String,
    @SerializedName("issn")
    val issn: String,
    @SerializedName("format")
    val format: String,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("urls")
    val urls: List<ComicUrls>,
    @SerializedName("series")
    val series: ComicSeries,
    @SerializedName("collections")
    val collections: List<String>,
    @SerializedName("dates")
    val dates: List<ComicDates>,
    @SerializedName("prices")
    val prices: List<ComicPrices>,
    @SerializedName("thumbnail")
    val thumbnail: ComicThumbnail,
    @SerializedName("images")
    val images: List<ComicImages>,
    @SerializedName("creators")
    val creators: ComicCreators,
    @SerializedName("characters")
    val characters: ComicCharacters,
    @SerializedName("stories")
    val stories: ComicStories,
    @SerializedName("events")
    val events: ComicEvents
)