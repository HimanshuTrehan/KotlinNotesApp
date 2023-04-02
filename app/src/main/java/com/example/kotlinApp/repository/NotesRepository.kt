package com.example.kotlinApp.repository

import androidx.lifecycle.LiveData
import com.example.kotlinApp.database.NotesDao
import com.example.kotlinApp.model.NotesEntityModel

class NotesRepository(val dao:NotesDao) {

    fun getAllNotes(): LiveData<List<NotesEntityModel>> {
        return dao.getNotesData()
    }

    suspend fun addNotes(notes:NotesEntityModel)
    {
        dao.addNotes(notes)
    }

    suspend fun updateNotes(notes:NotesEntityModel)
    {
        dao.updateNotes(notes)
    }

    suspend fun deleteNotes(id:Int)
    {
        dao.deleteNotes(id)
    }

}