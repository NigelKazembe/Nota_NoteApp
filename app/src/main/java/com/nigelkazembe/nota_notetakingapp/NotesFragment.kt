package com.nigelkazembe.nota_notetakingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.nigelkazembe.nota_notetakingapp.databinding.FragmentNotesBinding
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
class NotesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentNotesBinding? = null
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
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotesFragment().apply {
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

    override fun onStart() {
        super.onStart()
        arguments?.let {
            val args = NotesFragmentArgs.fromBundle(it)
            binding.titleText.setText(args.savedNote?.title)
            binding.editTextTextMultiLine3.setText(args.savedNote?.noteDetails)
        }
    }

    override fun onStop() {
        super.onStop()
        arguments?.let {
            val args = NotesFragmentArgs.fromBundle(it)
            args.savedNote?.title = binding.titleText.text.toString()
            args.savedNote?.noteDetails = binding.editTextTextMultiLine3.text.toString()
            args.savedNote?.lastModifiedDate = Calendar.getInstance().time

            viewModel.updateNotes(args.savedNote!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}