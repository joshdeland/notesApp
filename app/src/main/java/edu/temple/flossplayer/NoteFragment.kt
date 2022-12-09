package edu.temple.flossplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class NoteFragment : Fragment() {
    private lateinit var titleTextView: TextView
    private lateinit var bodyTextView: TextView
    private lateinit var imageView: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note, container, false).apply {
            titleTextView = findViewById(R.id.titleTextView)
            bodyTextView = findViewById(R.id.bodyTextView)
            imageView = findViewById(R.id.imageView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewModelProvider(requireActivity())[NoteViewModel::class.java]
            .getSelectedNote()?.observe(requireActivity()) {updateNote(it)}
    }

    private fun updateNote(note: Note?) {
        note?.run {
            titleTextView.text = title
            bodyTextView.text = body
        }
    }

}