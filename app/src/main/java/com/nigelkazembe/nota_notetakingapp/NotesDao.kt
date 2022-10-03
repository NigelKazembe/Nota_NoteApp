package com.nigelkazembe.nota_notetakingapp

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Dao
import androidx.room.Fts4
import androidx.room.Update
import androidx.lifecycle.MutableLiveData
import androidx.room.Delete


//import com.nigelkazembe.nota_notetakingapp.Notes
import com.nigelkazembe.nota_notetakingapp.Notes
import java.util.*

@Dao
interface NotesDao {
    @Insert
    fun insertNoteInfo(note: Notes)

    /*@Query("SELECT *, `rowid` FROM notes WHERE noteTitle OR noteDetails MATCH :keyValue")
    fun getNotes(keyValue: String): List<Notes>?*/

    @Query("SELECT * FROM notes_fts JOIN notes ON rowid = docid WHERE notes_fts MATCH :keyValue")
    fun getNotes(keyValue: String): List<Notes>?

    @Query("DELETE FROM notes WHERE rowid = :key")
    fun removeOrDeleteNote(key: Int)

    @Query("SELECT *, `rowid` FROM notes")
    fun getAllNotes(): LiveData<List<Notes>>?

    //You need to implement an update query which you will then call to then update the values when
    // you will have entered or modified the notes. Thus you will have to implement viewModel methods in the other
    // fragment classes that you will then use to update the database entries before closing the fragments
    //@Query("UPDATE ")
    //fun updateNote(key/rowid: Int, note: Note)
    @Query("UPDATE notes SET noteTitle = :title, noteDetails = :details, dateModified = :date WHERE rowid = :id")
    fun updateNoteData(id: Int, title: String, details: String, date: Date)


}