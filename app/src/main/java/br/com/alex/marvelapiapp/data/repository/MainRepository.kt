package br.com.alex.marvelapiapp.data.repository

import br.com.alex.marvelapiapp.data.datasource.remote.network.MarvelWebService
import br.com.alex.marvelapiapp.data.datasource.remote.response.characters.CharacterResult
import br.com.alex.marvelapiapp.data.datasource.remote.response.comics.ComicResults

class MainRepository(private val marvelWebService: MarvelWebService) : BaseRepository() {
    suspend fun getComics(): MutableList<ComicResults>? {
        return safeApiCall(
            call = { marvelWebService.requestComicsFromApi("comic", "thisMonth", "focDate") },
            error = "Erro ao buscar dados das HQs"
        )?.data?.results?.toMutableList()
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
}