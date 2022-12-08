package edu.temple.flossplayer

class BookList {

    private val bookList : ArrayList<Note> by lazy {
        ArrayList()
    }

    fun add(book: Note) {
        bookList.add(book)
    }

    fun remove (note: Note) {
        bookList.remove(note)
    }

    operator fun get(index: Int) = bookList[index]

    fun size() = bookList.size
}