package edu.temple.flossplayer

class NoteList(private var noteList: ArrayList<Note>){



    fun plus(note: Note) : NoteList {
        val updatedList = noteList.toMutableList()
        updatedList.add(note)
        return NoteList(updatedList as ArrayList<Note>)
    }

    fun setList(updatedNoteList: ArrayList<Note>) {
        noteList = updatedNoteList

    }

    operator fun get(index: Int) = noteList[index]

    fun size() = noteList.size
}