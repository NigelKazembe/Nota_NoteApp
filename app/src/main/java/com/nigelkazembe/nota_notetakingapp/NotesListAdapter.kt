package com.nigelkazembe.nota_notetakingapp

import android.annotation.SuppressLint
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.nigelkazembe.nota_notetakingapp.ui.main.MainFragmentDirections

class NotesListAdapter(private val contactItemLayout: Int): RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    private var notesList: List<Notes>? = null
    private var notePositionForAccess: Int = 0  //this variable is the one which I am going to make
                                                // use to return the note on a specific position when
                                                //the user clicks on that notes' specific card. This will help in the returning of the note from the database

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = holder.contactNme
        val item2 = holder.contactNum
        notePositionForAccess = holder.layoutPosition

        notesList.let {
            item.text = it!![position].title
            item2.text = it!![position].lastModifiedDate.toString()  + " " + it!![position].noteDetails
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.note_details, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (notesList == null) 0 else notesList!!.size
    }

    fun setNotesList(contacts: List<Notes>?) {
        notesList = contacts
        notifyDataSetChanged()
    }

    fun retList(): List<Notes> {
        return notesList!!
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
        var contactNme: TextView = itemView.findViewById(R.id.noteTitle)
        var contactNum: TextView = itemView.findViewById(R.id.noteDetails)
        var pos = 0

        init {
            itemView.setOnClickListener { v: View ->
                var position: Int = getAdapterPosition()

                val action: MainFragmentDirections.ActionMainFragmentToNotesFragment =
                    MainFragmentDirections.actionMainFragmentToNotesFragment(notesList!![position])
                //action.setSavedNote(notesList!![position])
                Navigation.findNavController(v).navigate(action)
            }
            itemView.setOnCreateContextMenuListener(this)

        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            view: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            pos =this.adapterPosition
            menu?.add(this.adapterPosition, 1, 0, "Delete" )
            //menu?.add(this.adapterPosition, 2, 1, "Pin" )
        }

    }
}