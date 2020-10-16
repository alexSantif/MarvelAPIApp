package br.com.data.datasource.remote.response.characters


import com.google.gson.annotations.SerializedName

data class CharacterResult(
    @SerializedName("comics")
    val characterComics: CharacterComics? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("events")
    val characterEvents: CharacterEvents? = null,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("series")
    val characterSeries: CharacterSeries? = null,
    @SerializedName("stories")
    val characterStories: CharacterStories? = null,
    @SerializedName("thumbnail")
    val characterThumbnail: CharacterThumbnail? = null,
    @SerializedName("urls")
    val characterUrls: List<CharacterUrl?>? = null
)