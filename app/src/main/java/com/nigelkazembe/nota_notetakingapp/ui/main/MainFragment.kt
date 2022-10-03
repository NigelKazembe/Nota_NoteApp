package com.nigelkazembe.nota_notetakingapp.ui.main

//import android.widget.SearchView

import android.content.res.Resources
import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.nigelkazembe.nota_notetakingapp.Notes
import com.nigelkazembe.nota_notetakingapp.NotesListAdapter
import com.nigelkazembe.nota_notetakingapp.R
import com.nigelkazembe.nota_notetakingapp.databinding.MainFragmentBinding

import java.util.*


class MainFragment : Fragment(), SearchView.OnQueryTextListener {
    private val viewModel: MainViewModel by viewModels()
    private var adapter: NotesListAdapter? = null
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        /*val rec = binding.notesRecyclerView as RecyclerView
        registerForContextMenu(rec)*/
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java) ##Not using this anymore because making use of viewModels() delegate method
        listenerSetup()
        observerSetup()
        recyclerSetup()

        setHasOptionsMenu(true)

    }

    /*override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }*/

    private fun recyclerSetup() {
        adapter = NotesListAdapter(R.layout.note_details)
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(context)//GridLayoutManager(context, 2)//LinearLayoutManager(context).....gridLayoutmanager accepts 2 arguments, one for the context and the other for the number of columns
        binding.notesRecyclerView.adapter = adapter
    }

    private fun listenerSetup() {
        binding.floatingActionButton.setOnClickListener {
            //var newNote = Notes(0,null,null, Date())
            var newNote = Notes()
            viewModel.insertNote(newNote)

            val action: MainFragmentDirections.ActionMainFragmentToNewNotesFragment =
                MainFragmentDirections.actionMainFragmentToNewNotesFragment(newNote)
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun observerSetup() {
        viewModel.getAllNotes()?.observe(viewLifecycleOwner, Observer { notes ->
            notes?.let {
                adapter?.setNotesList(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView

        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)


        super.onCreateOptionsMenu(menu, inflater)
    }

    //This will be triggered only when the search button is pressed/selected
    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null) {
            searchDaB(query)
        }
        return true
    }

    //This will be triggered when user typing
    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null) {
            searchDaB(query)
        }
        return true
    }

    private fun searchDaB(query: String) {
        val searchQuery = query
        viewModel.getSearchResults(query).observe(this) { list ->
            list.let {
                adapter?.setNotesList(it)
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var ret = adapter?.retList()
        if(item.title == "Delete") {
            viewModel.deleteNote(ret!![item.groupId])
        }
        return super.onContextItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}