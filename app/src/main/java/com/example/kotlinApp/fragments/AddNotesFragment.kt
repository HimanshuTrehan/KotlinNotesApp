package com.example.kotlinApp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.kotlinApp.model.NotesEntityModel
import com.example.kotlinApp.viewmodel.NotesViewModel
import com.example.lotlinApp.R
import com.example.lotlinApp.databinding.FragmentAddNotesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddNotesFragment : Fragment() {

lateinit var binding: FragmentAddNotesBinding

 var priority:Int = 0
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddNotesBinding.inflate(inflater,container,false)

        binding.addNotes.setOnClickListener {
                addNotesToDatabase(it)

        }
        binding.redCircle.setOnClickListener {

            priority = 0
            binding.redCircle.setImageResource(R.drawable.ic_check)
            binding.yellowCircle.setImageResource(0)
            binding.greenCircle.setImageResource(0)

        }
        binding.greenCircle.setOnClickListener {

            priority = 1
            binding.redCircle.setImageResource(0)
            binding.yellowCircle.setImageResource(0)
            binding.greenCircle.setImageResource(R.drawable.ic_check)
        }
        binding.yellowCircle.setOnClickListener {

            priority = 2
            binding.redCircle.setImageResource(0)
            binding.yellowCircle.setImageResource(R.drawable.ic_check)
            binding.greenCircle.setImageResource(0)

        }

        return binding.root



    }

    @SuppressLint("SimpleDateFormat")
    private  fun addNotesToDatabase(view: View) {

        val title = binding.titleText.text.toString()
        val subtitle = binding.subtitleText.text.toString()
        val notes = binding.notesTxt.text.toString()
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val date = Date()
        val currentDate = formatter.format(date)
        val createdNote = NotesEntityModel(null, title = title, subTitle = subtitle, notes = notes, date = currentDate, priority = priority)
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.addNotes(createdNote)
        }
        Navigation.findNavController(view).navigate(R.id.action_addNotesFragment_to_homeFragment)



    }

}