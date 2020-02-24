package br.com.alex.marvelapiapp.data.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.alex.marvelapiapp.data.datasource.database.dao.MarvelDao
import br.com.alex.marvelapiapp.data.datasource.entity.Comic
import br.com.alex.marvelapiapp.utils.Constants

@Database(
    entities = [Comic::class],
    version = 1,
    exportSchema = false
)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun marvelDao(): MarvelDao

    companion object {
        private var INSTANCE: MarvelDatabase? = null

        fun getInstance(context: Context): MarvelDatabase? {
            if (INSTANCE == null) {
                synchronized(MarvelDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MarvelDatabase::class.java,
                        Constants.MARVEL_DATABASE
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}