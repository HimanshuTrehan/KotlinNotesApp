package com.example.kotlinApp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "Notes")
class NotesEntityModel (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title:String?,
    var subTitle:String?,
    var notes:String? ,
    var date:String? ,
    var priority:Int?

) : Parcelable