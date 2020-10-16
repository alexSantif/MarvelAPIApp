package br.com.data.repository

import android.util.Log
import br.com.data.datasource.remote.network.Output
import java.io.IOException

open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call: suspend () -> T, error: String): T? {
        val result = marvelApiOutput(call, error)
        var output: T? = null
        when (result) {
            is Output.Success -> output = result.output
            is Output.Error -> Log.e("Error", error)
        }
        return output
    }

    private suspend fun <T : Any> marvelApiOutput(
        call: suspend () -> T,
        error: String
    ): Output<T> {
        return try{
            Output.Success(call.invoke())
        }catch (e: Exception){
            Output.Error(IOException(error))
        }
    }
}