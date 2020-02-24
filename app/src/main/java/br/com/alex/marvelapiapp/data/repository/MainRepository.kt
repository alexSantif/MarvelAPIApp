package br.com.alex.marvelapiapp.data.repository

import br.com.alex.marvelapiapp.MarvelApplication
import br.com.alex.marvelapiapp.data.datasource.database.MarvelDatabase
import br.com.alex.marvelapiapp.data.datasource.entity.Comic
import br.com.alex.marvelapiapp.data.datasource.mapper.MarvelMapper
import br.com.alex.marvelapiapp.data.datasource.remote.network.MarvelWebService
import br.com.alex.marvelapiapp.data.datasource.remote.response.characters.CharacterResult
import br.com.alex.marvelapiapp.data.datasource.remote.response.comics.ComicResults
import br.com.alex.marvelapiapp.utils.isNetworkAvailable

class MainRepository(private val marvelWebService: MarvelWebService) : BaseRepository() {
    private val context = MarvelApplication.appContext
    private val marvelDao by lazy {
        MarvelDatabase.getInstance(context)?.marvelDao()
    }
    suspend fun getComics(): MutableList<Comic>? {
        if (isNetworkAvailable()) {
            val response = safeApiCall(
                call = { marvelWebService.requestComicsFromApi("comic", "thisMonth", "focDate") },
                error = "Erro ao buscar dados das HQs"
            )

            val responseTransformed = MarvelMapper().transform(response)
            saveComics(responseTransformed)
            return responseTransformed
        }
        return marvelDao?.getAll()?.toMutableList()
    }

    suspend fun getCharacterByName(characterName: String): MutableList<CharacterResult>? {
        return safeApiCall(
            call = { marvelWebService.getCharacterByName(characterName) },
            error = "Erro ao buscar dados da personagem"
        )?.characterData?.characterResults?.toMutableList()
    }

    suspend fun getCharacterComics(characterId: Int): MutableList<ComicResults>? {
        return safeApiCall(
            call = { marvelWebService.getCharacterComics(characterId.toString()) },
            error = "Erro ao buscar HQs da personagem"
        )?.data?.results?.toMutableList()
    }

    private suspend fun saveComics(comicsList: List<Comic>) {
        marvelDao?.insertOrUpdateList(comicsList)
    }
}