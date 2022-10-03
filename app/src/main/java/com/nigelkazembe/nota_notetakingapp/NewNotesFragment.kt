package com.nigelkazembe.nota_notetakingapp

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nigelkazembe.nota_notetakingapp.databinding.FragmentNewnotesBinding
import com.nigelkazembe.nota_notetakingapp.ui.main.MainViewModel
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewNotesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentNewnotesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewnotesBinding.inflate(inflater, container, false)
        /*val activity = activity as? MainActivity
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)*/
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewNotesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.appBar.inflateMenu(R.menu.back_btn)
        binding.appBar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        binding.appBar.setNavigationOnClickListener { view ->
            activity?.onBackPressed()
        }
    }

    override fun onStop() {
        super.onStop()
        arguments?.let {
            val args = NewNotesFragmentArgs.fromBundle(it)
            args.newUserNoteCreatedObject?.title = binding.newTitleText.text.toString()
            args.newUserNoteCreatedObject?.noteDetails = binding.newEditTextTextMultiLine3.text.toString()
            args.newUserNoteCreatedObject?.lastModifiedDate = Calendar.getInstance().time

            args.newUserNoteCreatedObject?.let { note ->
                viewModel.updateNotes(note)
            }
            //viewModel.updateNotes(args.newUserNoteCreatedObject!!)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        arguments?.let {
            val args = NewNotesFragmentArgs.fromBundle(it)
            args.newUserNoteCreatedObject?.title = binding.newTitleText.text.toString()
            args.newUserNoteCreatedObject?.noteDetails = binding.newEditTextTextMultiLine3.text.toString()
            args.newUserNoteCreatedObject?.lastModifiedDate = Calendar.getInstance().time

            args.newUserNoteCreatedObject?.let { note ->
                viewModel.updateNotes(note)
            }
        }

        (activity as AppCompatActivity).supportActionBar?.show()
    }
    override fun onDetach() {
        super.onDetach()
    }
}