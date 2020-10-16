package br.com.data.datasource.remote.network

import br.com.data.datasource.remote.response.characters.CharacterResponse
import br.com.data.datasource.remote.response.comics.ComicsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelWebService {
    @GET("v1/public/comics")
    suspend fun requestComicsFromApi(
        @Query("format") format: String,
        @Query("dateDescriptor") dateDescriptor: String,
        @Query("orderBy") orderBy: String
    ): ComicsResponse

    @GET("v1/public/characters")
    suspend fun getCharacterByName(
        @Query("name") name: String
    ): CharacterResponse

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId") characterId: String
    ): ComicsResponse
}