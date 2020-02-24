package br.com.alex.marvelapiapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.alex.marvelapiapp.data.datasource.entity.Comic
import br.com.alex.marvelapiapp.data.datasource.remote.network.RetrofitBuilder
import br.com.alex.marvelapiapp.data.datasource.remote.response.characters.CharacterResult
import br.com.alex.marvelapiapp.data.datasource.remote.response.comics.ComicResults
import br.com.alex.marvelapiapp.data.repository.MainRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val mainRepository: MainRepository = MainRepository(RetrofitBuilder.marvelWebService)
    val comicsLiveData = MutableLiveData<MutableList<Comic>>()
    val characterLiveData = MutableLiveData<MutableList<CharacterResult>>()
    val characterComicsLiveData = MutableLiveData<MutableList<ComicResults>>()

    fun getComics() {
        scope.launch {
            val comics = mainRepository.getComics()
            comicsLiveData.postValue(comics)
        }
    }

    fun getCharacterByName(characterName: String) {
        scope.launch {
            val character = mainRepository.getCharacterByName(characterName)
            characterLiveData.postValue(character)
        }
    }

    fun getCharacterComics(characterId: Int) {
        scope.launch {
            val characterComics = mainRepository.getCharacterComics(characterId)
            characterComicsLiveData.postValue(characterComics)
        }
    }

    fun cancelRequests() = coroutineContext.cancel()
}