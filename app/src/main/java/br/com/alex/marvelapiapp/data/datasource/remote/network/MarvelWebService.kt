package br.com.alex.marvelapiapp.data.datasource.remote.network

import br.com.alex.marvelapiapp.data.datasource.remote.response.characters.CharacterResponse
import br.com.alex.marvelapiapp.data.datasource.remote.response.comics.ComicsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelWebService {
    @GET("v1/public/comics")
    suspend fun requestComicsFromApi(
        @Query("format") format: String,
        @Query("dateDescriptor") dateDescriptor: String,
        @Query("orderBy") orderBy: String
    ): Response<ComicsResponse>

    @GET("v1/public/characters")
    suspend fun getCharacterByName(
        @Query("name") name: String
    ): Response<CharacterResponse>

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId") characterId: String
    ): Response<ComicsResponse>
}