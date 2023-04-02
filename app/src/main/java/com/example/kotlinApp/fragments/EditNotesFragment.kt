package com.example.kotlinApp.fragments

import android.os.Bundle
import android.view.*
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.kotlinApp.viewmodel.NotesViewModel
import com.example.lotlinApp.R
import com.example.lotlinApp.databinding.FragmentEditNotesBinding
import com.example.lotlinApp.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditNotesFragment : Fragment() {


  lateinit var binding:FragmentEditNotesBinding
  val data by navArgs<EditNotesFragmentArgs>()
    val viewModel:NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(inflater,container,false)

        binding.titleText.setText(data.data.title)
        binding.subtitleText.setText(data.data.subTitle)
        binding.notesTxt.setText(data.data.notes)

        setHasOptionsMenu(true)
        when (data.data.priority) {
            1 -> {
               binding.greenCircle.setImageResource(R.drawable.ic_check)
            }
            2 -> {
               binding.yellowCircle.setImageResource(R.drawable.ic_check)
            }
            else -> {
               binding.redCircle.setImageResource(R.drawable.ic_check)
            }
        }


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
         inflater.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.deleteNote)
        {
           deleteNotes()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNotes() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.deleteNote(data.data.id!!)
        }

        this.view?.let { Navigation.findNavController(it).navigate(R.id.action_editNotesFragment_to_homeFragment) }

    }
}