package br.com.data.datasource.mapper

import br.com.data.datasource.remote.response.comics.ComicsResponse

class MarvelMapper {
    fun transform(comicsResponse: ComicsResponse?): ArrayList<br.com.data.datasource.entity.Comic> {
        val comicsList = ArrayList<br.com.data.datasource.entity.Comic>()

        comicsResponse?.data?.results?.let {
            for ((index, result) in it.withIndex()) {
                comicsList.add(
                    br.com.data.datasource.entity.Comic(
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