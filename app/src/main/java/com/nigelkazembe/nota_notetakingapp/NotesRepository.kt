package com.nigelkazembe.nota_notetakingapp

import android.app.Application
import com.nigelkazembe.nota_notetakingapp.NotesRoomDatabase
import android.content.Context
import kotlinx.coroutines.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NotesRepository(application: Application) {
    val searchResults = MutableLiveData<List<Notes>>()
    private var notesDao: NotesDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allNotes: LiveData<List<Notes>>?

    init{
        val roomDBInstance: NotesRoomDatabase? = NotesRoomDatabase.getDatabase(application)
        notesDao = roomDBInstance?.noteDao()
        allNotes = notesDao?.getAllNotes()
    }

    //Tnis method is responsible for inserting the data into the database
    private suspend fun insertNote(note: Notes){
        notesDao?.insertNoteInfo(note)
    }

    fun insertIntoDB(noteInfo: Notes) {
        coroutineScope.launch(Dispatchers.IO) {
            insertNote(noteInfo)
        }
    }

    //The method to update the database or a specific row actually
    private suspend fun updateNote(note: Notes) {

        notesDao?.updateNoteData(note.rowid, note.title!!, note.noteDetails!!, note.lastModifiedDate)
    }

    fun updateNoteDB(note: Notes) {
        coroutineScope.launch(Dispatchers.IO) {
            updateNote(note)
        }
    }

    //The method responsible for searching the database and deleting the record with a specified key
    private suspend fun deleteForNotesWithKey(searchKey: Int) {
        notesDao?.removeOrDeleteNote(searchKey)
    }

    fun delete(key: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            deleteForNotesWithKey(key)
        }
    }

    fun searchForNotes(searchKeyVal: String) {
        coroutineScope.launch(Dispatchers.IO) {
            searchResults.postValue(findNotes(searchKeyVal).await())
        }
    }

    private suspend fun findNotes(searchKey: String): Deferred<List<Notes>?> = coroutineScope.async(Dispatchers.IO) {
        return@async notesDao?.getNotes(searchKey)
    }
}