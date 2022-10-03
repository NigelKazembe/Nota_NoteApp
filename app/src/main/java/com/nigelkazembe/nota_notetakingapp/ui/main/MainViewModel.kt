package com.nigelkazembe.nota_notetakingapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nigelkazembe.nota_notetakingapp.Notes
import com.nigelkazembe.nota_notetakingapp.NotesRepository

//To ensure that the pinned state works well, we can make use of a field in the database that will be updated when the user
//selects the note to be pinned and it'll then be changed to either boolean values or integer values 0 and 1

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotesRepository = NotesRepository(application)
    private val allNotes: LiveData<List<Notes>>?
    private val searchResults: MutableLiveData<List<Notes>>

    init{
        allNotes = repository.allNotes
        searchResults = repository.searchResults
    }

    fun insertNote(note: Notes){
        repository.insertIntoDB(note)
    }

    fun deleteNote(note: Notes) {
        repository.delete(note.rowid!!)
    }

    fun getSearchResults(keyVal:String): MutableLiveData<List<Notes>> {
        repository.searchForNotes(keyVal)//
        return searchResults
    }

    fun getAllNotes(): LiveData<List<Notes>>? {
        return allNotes
    }

    fun updateNotes(note: Notes) {
        repository.updateNoteDB(note)
    }
}