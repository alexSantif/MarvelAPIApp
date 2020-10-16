package br.com.alex.marvelapiapp.data.repository

import br.com.data.datasource.remote.response.comics.ComicsResponse
import br.com.data.repository.BaseRepository
import io.mockk.mockk
import org.junit.Test
import retrofit2.Response

class MainRepositoryTest : BaseRepository() {

    @Test
    fun should_returnComicsList_whenGetComicsApiCallReturnsSuccess() {
        val comicsReturn = mockk<Response<ComicsResponse>>()


    }

    @Test
    fun should_returnCharacterData_whenGetCharacterByNameApiCallReturnsSuccess() {

    }

    @Test
    fun should_returnCharacterComicsList_whenGetCharacterComicsApiCallReturnsSuccess() {

    }

    @Test
    fun should_saveComicsIntoDatabase_whenGetComicsApiCallReturnsSuccess() {

    }
}