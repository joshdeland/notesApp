package edu.temple.flossplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider

class NewNoteFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        noteViewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_note, container, false)

        // Set up the EditText views for the user to enter the note's title and body
        val titleEditText = view.findViewById(R.id.editTextTitle) as EditText
        val bodyEditText = view.findViewById(R.id.editTextBody) as EditText

        // Set up a button for the user to save the new note
        val saveButton = view.findViewById(R.id.saveButton) as Button
        saveButton.setOnClickListener {

            // Get the title and body entered by the user

            val title = titleEditText.text.toString()
            val body = bodyEditText.text.toString()

            // Add the new note to the noteViewModel
            noteViewModel.addNote(title, body)

        }
        return view
    }
}
