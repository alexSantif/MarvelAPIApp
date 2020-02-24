package br.com.alex.marvelapiapp.data.datasource.database.dao

import androidx.room.*
import br.com.alex.marvelapiapp.data.datasource.entity.Comic

@Dao
interface MarvelDao {
    @Query("SELECT * FROM comics")
    suspend fun getAll(): List<Comic>

    @Query("SELECT * FROM comics WHERE creation_date = :creation_date ORDER BY creation_date DESC")
    suspend fun getById(creation_date: String): Comic

    @Delete
    suspend fun delete(comic: Comic)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(comic: Comic)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateList(comics: List<Comic>)
}