package edu.temple.flossplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var createNoteButton: FloatingActionButton

    private val isSingleContainer : Boolean by lazy{
        findViewById<View>(R.id.container2) == null
    }

    private val noteViewModel : NoteViewModel by lazy {
        ViewModelProvider(this)[NoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNoteButton = findViewById(R.id.floatingActionButton)

        noteViewModel.setNoteList(getNoteList())

        createNoteButton.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, NewNoteFragment())
                .addToBackStack(null)
                .commit()
        }

        // If we're switching from one container to two containers
        // clear BookPlayerFragment from container1
        if (supportFragmentManager.findFragmentById(R.id.container1) is BookPlayerFragment) {
            supportFragmentManager.popBackStack()
        }

        // If this is the first time the activity is loading, go ahead and add a BookListFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container1, NoteListFragment())
                .commit()
        } else
        // If activity loaded previously, there's already a BookListFragment
        // If we have a single container and a selected book, place it on top
            if (isSingleContainer && noteViewModel.getSelectedNote()?.value != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, BookPlayerFragment())
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit()
            }

        // If we have two containers but no BookPlayerFragment, add one to container2
        if (!isSingleContainer && supportFragmentManager.findFragmentById(R.id.container2) !is BookPlayerFragment)
            supportFragmentManager.beginTransaction()
                .add(R.id.container2, BookPlayerFragment())
                .commit()


        // Respond to selection in portrait mode using flag stored in ViewModel
        noteViewModel.getSelectedNote()?.observe(this){
            if (!noteViewModel.hasViewedSelectedNote()) {
                if (isSingleContainer) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container1, BookPlayerFragment())
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit()
                }
                noteViewModel.markSelectedNoteViewed()
            }
        }
    }

    override fun onBackPressed() {
        // BackPress clears the selected book
        noteViewModel.clearSelectedNote()
        super.onBackPressed()
    }

    private fun getNoteList() : NoteList {
        val noteList = NoteList()
        repeat (10) {
            noteList.add(Note("Note title ${it + 1}", "Body ${10 - it}"))
        }

        return noteList
    }
}