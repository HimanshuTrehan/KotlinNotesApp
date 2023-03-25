package com.example.kotlinApp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kotlinApp.database.NotesDataBase
import com.example.kotlinApp.model.NotesEntityModel
import com.example.kotlinApp.repository.NotesRepository

class NotesViewModel(application: Application):AndroidViewModel(application) {

    val repository: NotesRepository

    init {
        val dao = NotesDataBase.getDatabase(application).notesDao()
        repository = NotesRepository(dao)
    }


    fun getAllNotes():LiveData<List<NotesEntityModel>> = repository.getAllNotes()

   suspend fun addNotes(notes:NotesEntityModel)
    {
        repository.addNotes(notes)
    }

    suspend fun updateNotes(notes:NotesEntityModel)
    {
        repository.updateNotes(notes)
    }

    fun deleteNote(id:Int)
    {
        repository.deleteNotes(id)
    }


}