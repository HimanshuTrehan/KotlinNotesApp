package com.example.kotlinApp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.kotlinApp.model.NotesEntityModel

@Dao
interface NotesDao {



    @Query("SELECT * FROM Notes")
    fun getNotesData():LiveData<List<NotesEntityModel>>

    @Insert(onConflict = REPLACE)
    suspend fun addNotes(notes:NotesEntityModel)


    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id:Int)

    @Update(onConflict = REPLACE)
   suspend fun updateNotes(notes:NotesEntityModel)



}