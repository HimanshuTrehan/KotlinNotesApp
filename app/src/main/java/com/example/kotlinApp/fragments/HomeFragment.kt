package com.example.kotlinApp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.kotlinApp.adapter.NotesAdapter
import com.example.kotlinApp.model.NotesEntityModel
import com.example.kotlinApp.viewmodel.NotesViewModel
import com.example.lotlinApp.R
import com.example.lotlinApp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    private val viewModel:NotesViewModel by viewModels()
   lateinit var oldNotes : List<NotesEntityModel>
   lateinit var adapter : NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.addNotes.setOnClickListener {

        Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_addNotesFragment)
            
        }
        setHasOptionsMenu(true)

        viewModel.getAllNotes().observe(viewLifecycleOwner) { notesList ->
            if (notesList.isNotEmpty()) {
                oldNotes = notesList
                binding.label.visibility = GONE
                binding.notesRecycler.visibility = VISIBLE
                binding.notesRecycler.layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.notesRecycler.adapter = adapter
            }else{
                binding.notesRecycler.visibility = GONE
                binding.label.visibility = VISIBLE
            }
        }


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item = menu.findItem(R.id.search_view)
        val search = item.actionView as SearchView

        search.queryHint = "Enter text to search"


        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchText: String?): Boolean {
                if (oldNotes.isNotEmpty()) {
                    filterNotes(searchText)
                }
             return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun filterNotes(searchText: String?) {
        var searchedNotes = (arrayListOf <NotesEntityModel>())
        for (i in oldNotes) {
            if (i.title?.contains(searchText!!) == true || i.subTitle?.contains(searchText!!) == true)
            {
                searchedNotes.add(i)
            }
        }
        updatedList(searchedNotes)

    }

    private fun updatedList(searchedNotes: ArrayList<NotesEntityModel>) {
        adapter.filtering(searchedNotes)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

//        if(item.itemId == R.id.search_view)
//        {
//          //  deleteNotes()
//
//        }
        return super.onOptionsItemSelected(item)
    }


}