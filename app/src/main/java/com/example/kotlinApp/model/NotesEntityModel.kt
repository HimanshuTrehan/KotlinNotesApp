package com.example.kotlinApp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "Notes")
class NotesEntityModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title:String?,
    var subTitle:String?,
    var notes:String? ,
    var date:String? ,
    var priority:Int?

)