package br.com.data.repository

import br.com.alex.marvelapiapp.MarvelApplication
import br.com.data.datasource.database.MarvelDatabase
import br.com.data.datasource.entity.Comic
import br.com.data.datasource.mapper.MarvelMapper
import br.com.data.datasource.remote.network.MarvelWebService
import br.com.data.datasource.remote.response.characters.CharacterResult
import br.com.data.datasource.remote.response.comics.ComicResults

class MainRepository(private val marvelWebService: MarvelWebService) :
    BaseRepository() {
    private val context = MarvelApplication.appContext
    private val marvelDao by lazy {
        MarvelDatabase.getInstance(context)?.marvelDao()
    }

    suspend fun getComics(): MutableList<Comic>? {
        if (br.com.sharedutils.isNetworkAvailable()) {
            val response = safeApiCall(
                call = { marvelWebService.requestComicsFromApi("comic", "thisMonth", "focDate") },
                error = "Erro ao buscar dados das HQs"
            )

            val responseTransformed = MarvelMapper().transform(response)
            saveComics(responseTransformed)
            return responseTransformed
        }
        return marvelDao?.getAllItems()?.toMutableList()
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