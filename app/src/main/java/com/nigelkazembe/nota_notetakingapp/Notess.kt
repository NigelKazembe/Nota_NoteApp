package com.nigelkazembe.nota_notetakingapp
import androidx.annotation.NonNull
import androidx.room.*
import java.io.Serializable
import java.util.*

@Fts4(contentEntity = Notes::class)
@Entity(tableName = "notes_fts")
data class Notess(
    @PrimaryKey(autoGenerate = true)
    //@NonNull
    @ColumnInfo(name = "rowid")
    var rowid: Int? = 0,

    @ColumnInfo(name="noteTitle")
    var title: String?,

    @ColumnInfo(name="noteDetails")
    var noteDetails: String?
)


/*

    */
/*@NonNull
    @ColumnInfo(name = "pinnedNote")
    var pinned: Boolean? = true*//*


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


}*/
