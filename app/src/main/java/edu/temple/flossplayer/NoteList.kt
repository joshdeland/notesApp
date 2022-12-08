package edu.temple.flossplayer

class NoteList {

    private val noteList : ArrayList<Note> by lazy {
        ArrayList()
    }

    fun add(note: Note) {
        noteList.add(note)
    }

    fun remove (note: Note) {
        noteList.remove(note)
    }

    operator fun get(index: Int) = noteList[index]

    fun size() = noteList.size
}