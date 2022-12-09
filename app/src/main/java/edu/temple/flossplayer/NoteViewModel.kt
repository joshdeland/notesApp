package edu.temple.flossplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {

    private val selectedNote: MutableLiveData<Note>? by lazy {
        MutableLiveData()
    }

    private val noteList: MutableLiveData<NoteList> by lazy {
        MutableLiveData()
    }

    // Flag to determine if one-off event should fire
    private var viewedNote = false

    fun getSelectedNote(): LiveData<Note>? {
        return selectedNote
    }

    fun setSelectedNote(selectedNote: Note) {
        viewedNote = false
        this.selectedNote?.value = selectedNote
    }

    fun clearSelectedNote () {
        selectedNote?.value = null
    }

    fun markSelectedNoteViewed () {
        viewedNote = true
    }

    fun hasViewedSelectedNote() : Boolean {
        return viewedNote
    }

    fun getNoteList(): LiveData<NoteList> {
        return noteList
    }

    fun setNoteList(noteList: NoteList) {
        this.noteList.value = noteList
    }
    fun addNote(title: String, body: String) {
        val newNote = Note(title,body)
        val updatedNoteList = noteList.value?.plus(newNote)
        if (updatedNoteList != null) {
            setNoteList(updatedNoteList)
        }


    }
}