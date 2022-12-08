package edu.temple.flossplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {

    private val selectedBook: MutableLiveData<Note>? by lazy {
        MutableLiveData()
    }

    private val bookList: MutableLiveData<BookList> by lazy {
        MutableLiveData()
    }

    // Flag to determine if one-off event should fire
    private var viewedBook = false

    fun getSelectedBook(): LiveData<Note>? {
        return selectedBook
    }

    fun setSelectedBook(selectedBook: Note) {
        viewedBook = false
        this.selectedBook?.value = selectedBook
    }

    fun clearSelectedBook () {
        selectedBook?.value = null
    }

    fun markSelectedBookViewed () {
        viewedBook = true
    }

    fun hasViewedSelectedBook() : Boolean {
        return viewedBook
    }

    fun getBookList(): LiveData<BookList> {
        return bookList
    }

    fun setBookList(bookList: BookList) {
        this.bookList.value = bookList
    }
}