package br.com.alex.marvelapiapp.data.repository

import android.util.Log
import br.com.alex.marvelapiapp.data.datasource.remote.network.Output
import retrofit2.Response
import java.io.IOException

open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, error: String): T? {
        val result = marvelApiOutput(call, error)
        var output: T? = null
        when (result) {
            is Output.Success ->
                output = result.output
            is Output.Error -> Log.e("Erro", "Erro: $error . Exception: ${result.exception}")
        }
        return output
    }

    private suspend fun <T : Any> marvelApiOutput(
        call: suspend () -> Response<T>,
        error: String
    ): Output<T> {
        val response = call.invoke()
        return if (response.isSuccessful)
            Output.Success(response.body()!!)
        else
            Output.Error(IOException("Algo deu errado. Código do erro:  ${response.code()} . Erro: $error"))
    }
}