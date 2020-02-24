package br.com.alex.marvelapiapp.data.datasource.mapper

import br.com.alex.marvelapiapp.data.datasource.entity.Comic
import br.com.alex.marvelapiapp.data.datasource.remote.response.comics.ComicsResponse

class MarvelMapper {
    fun transform(comicsResponse: ComicsResponse?): ArrayList<Comic> {
        val comicsList = ArrayList<Comic>()

        comicsResponse?.data?.results?.let {
            for ((index, result) in it.withIndex()) {
                comicsList.add(
                    Comic(
                        creation_date = index.toString(),
                        title = result.title,
                        description = result.description,
                        imageUrl = result.thumbnail.path,
                        imageExtension = result.thumbnail.extension
                    )
                )
            }
        }
        return comicsList
    }
}