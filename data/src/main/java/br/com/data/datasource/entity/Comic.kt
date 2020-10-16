package br.com.data.datasource.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "comics", indices = [Index("creation_date")])
data class Comic(
    @PrimaryKey
    val creation_date: String,
    var title: String? = null,
    var description: String? = null,
    var imageUrl: String? = null,
    var imageExtension: String? = null
) : Serializable