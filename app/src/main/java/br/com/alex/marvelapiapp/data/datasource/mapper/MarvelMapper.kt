package br.com.alex.marvelapiapp.data.datasource.mapper

import br.com.alex.marvelapiapp.data.datasource.entity.Comic
import br.com.alex.marvelapiapp.data.datasource.remote.response.comics.ComicsResponse
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MarvelMapper {

    fun transform(comicsResponse: ComicsResponse?): ArrayList<Comic> {
        val list = ArrayList<Comic>()

        comicsResponse?.data?.results?.let {
            for ((i, result) in it.withIndex()) {
                list.add(
                    Comic(
                        creation_date = i.toString(),
                        title = result.title,
                        description = result.description,
                        imageUrl = result.thumbnail.path,
                        imageExtension = result.thumbnail.extension
                    )
                )
            }
        }

        return list
    }

}