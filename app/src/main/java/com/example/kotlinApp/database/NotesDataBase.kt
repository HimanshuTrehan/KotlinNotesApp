package com.example.kotlinApp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinApp.model.NotesEntityModel
@Database(entities = [NotesEntityModel::class], version = 1, exportSchema = false)
abstract  class NotesDataBase: RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {

        @Volatile
        private var INSTANCE: NotesDataBase? = null

        fun getDatabase(context: Context): NotesDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): NotesDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                NotesDataBase::class.java,
                "notes_database"
            )
                .build()
        }
    }

}