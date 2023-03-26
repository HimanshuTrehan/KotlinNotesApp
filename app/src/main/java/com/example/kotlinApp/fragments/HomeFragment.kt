package com.example.kotlinApp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.kotlinApp.viewmodel.NotesViewModel
import com.example.lotlinApp.R
import com.example.lotlinApp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    val viewModel:NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.addNotes.setOnClickListener {

        Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_addNotesFragment)
            
        }

        viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->

            for (notesData in notesList) {
                Log.e("Notes", "" + notesData.title)
            }
        }


        return binding.root
    }

}