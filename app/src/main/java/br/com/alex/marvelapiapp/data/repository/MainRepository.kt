package br.com.alex.marvelapiapp.data.repository

import br.com.alex.marvelapiapp.data.datasource.remote.network.MarvelWebService
import br.com.alex.marvelapiapp.data.datasource.remote.response.comics.ComicResults

class MainRepository(private val marvelWebService: MarvelWebService) : BaseRepository() {
    suspend fun getComics() :  MutableList<ComicResults>?{
        return safeApiCall(
            call = {marvelWebService.requestComicsFromApi("comic", "thisMonth", "focDate")},
            error = "Error fetching data"
        )?.data?.results?.toMutableList()
    }
}