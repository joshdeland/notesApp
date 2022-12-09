package edu.temple.flossplayer

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class NewNoteFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel
    lateinit var noteImageSelected: ImageView
    var imageUri: Uri? = null

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
        val titleEditText = view.findViewById<EditText>(R.id.editTextTitle)
        val bodyEditText = view.findViewById<EditText>(R.id.editTextBody)
        noteImageSelected = view.findViewById(R.id.newImageView)
        val selectImageButton = view.findViewById<Button>(R.id.selectImageButton)
        noteImageSelected.visibility = View.GONE

        selectImageButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)
        }

        // Set up a button for the user to save the new note
        val saveButton = view.findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {

            // Get the title and body entered by the user

            val title = titleEditText.text.toString()
            val body = bodyEditText.text.toString()
            val image = imageUri

            // Add the new note to the noteViewModel
            noteViewModel.addNote(title, body, image)

        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 1) {
            imageUri = data?.data
            if (imageUri != null) {
                noteImageSelected.setImageDrawable(null)
                Picasso.get().load(imageUri).into(noteImageSelected)
                noteImageSelected.visibility = View.VISIBLE
            }
        }
    }
}
