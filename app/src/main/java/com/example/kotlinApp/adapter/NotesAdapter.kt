package com.example.kotlinApp.adapter

import android.content.Context
import android.provider.CalendarContract.Colors
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinApp.fragments.HomeFragmentDirections
import com.example.kotlinApp.model.NotesEntityModel
import com.example.lotlinApp.R
import com.example.lotlinApp.databinding.NotesItemViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesAdapter(val context: Context,var notesList: List<NotesEntityModel>) :RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(val binding: NotesItemViewBinding) : RecyclerView.ViewHolder(binding.root)
    {

    }

    fun filtering(filteredNotesList: List<NotesEntityModel>)
    {
        notesList = filteredNotesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
       return  NotesViewHolder(NotesItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder( holder: NotesViewHolder, position: Int) {

        var data = notesList[position]
        holder.binding.titleTxt.text = data.title
        holder.binding.subtitleTxt.text = data.subTitle
        holder.binding.notesTxt.text = data.notes
        holder.binding.dateTxt.text = data.date

        when (data.priority) {
            1 -> {
                holder.binding.priority.setBackgroundResource(R.drawable.green_circle)
            }
            2 -> {
                holder.binding.priority.setBackgroundResource(R.drawable.yellow_circle)
            }
            else -> {
                holder.binding.priority.setBackgroundResource(R.drawable.red_circle)
            }
        }

        holder.binding.root.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return  notesList.size
    }


}