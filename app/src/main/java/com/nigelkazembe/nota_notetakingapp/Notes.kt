package com.nigelkazembe.nota_notetakingapp

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
//import kotlinx.serialization.Serializable
import java.util.*
import java.io.Serializable


//@Fts4(contentEntity = Notes::class)
//@Fts4
@Entity(tableName = "notes")
//@Serializable
/*data class Notes(
    @PrimaryKey(autoGenerate = true)
    //@NonNull
    @ColumnInfo(name = "rowid")
    var rowid:Int = 0,
    @ColumnInfo(name = "noteTitle")
    var title: String?,
    @ColumnInfo(name="noteDetails")
    var noteDetails: String?,
    @ColumnInfo(name = "dateModified")
    var lastModifiedDate: Date
) : Serializable*/

class Notes: Serializable {

    @PrimaryKey(autoGenerate = true)
    //@NonNull
    @ColumnInfo(name = "rowid")
    var rowid:Int = 0
    @ColumnInfo(name = "noteTitle")
    var title: String? = ""
    @ColumnInfo(name="noteDetails")
    var noteDetails: String? = ""
    @ColumnInfo(name = "dateModified")
    var lastModifiedDate: Date = Date()

   /* @NonNull
    @ColumnInfo(name = "pinnedNote")
    var pinned: Boolean? = true*/


    constructor() {}

    constructor(noteID: Int, title: String, note: String, date: Date) {
        this.rowid = noteID
        this.title = title
        this.noteDetails = note
        this.lastModifiedDate = date
    }

    constructor(title: String, note: String, date: Date) {
        this.title = title
        this.noteDetails = note
        this.lastModifiedDate = date
    }

    constructor(note: String, date: Date) {
        this.noteDetails = note
        this.lastModifiedDate = date
    }

    constructor(date: Date) {
        this.lastModifiedDate = date
    }


}
