package br.com.alex.marvelapiapp.data.repository

import br.com.alex.marvelapiapp.data.datasource.remote.response.comics.ComicsResponse
import io.mockk.mockk
import org.junit.Assert.*
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