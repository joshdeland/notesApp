package edu.temple.flossplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NoteListFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        noteViewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onClick: (Note) -> Unit = {
            // Update the ViewModel
                note: Note ->
            noteViewModel.setSelectedNote(note)
            // Inform the activity of the selection so as to not have the event replayed
            // when the activity is restarted
        }

        with(view as RecyclerView) {
            layoutManager = GridLayoutManager(requireActivity(), 2)

            noteViewModel.getNoteList().observe(requireActivity()) {
                adapter = NoteListAdapter(it, onClick)
            }
        }


    }

    class NoteListAdapter(_noteList: NoteList, _onClick: (Note) -> Unit) :
        RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {
        private val noteList = _noteList
        private val onClick = _onClick

        inner class NoteViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {
            val titleTextView: TextView = layout.findViewById(R.id.titleTextView)
            val bodyTextView: TextView = layout.findViewById(R.id.bodyTextView)
            val noteImageView: ImageView = layout.findViewById(R.id.noteImageView)

            init {
                layout.setOnClickListener {
                    onClick(noteList[adapterPosition])
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
            return NoteViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.notelist_items_layout, parent, false)
            )
        }

        // Bind the note to the holder along with the values for the views
        override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
            holder.titleTextView.text = noteList[position].title
            holder.bodyTextView.text = noteList[position].body
            Picasso.get().load(noteList[position].imageURI).into(holder.noteImageView)
        }

        override fun getItemCount(): Int {
            return noteList.size()
        }

    }

}