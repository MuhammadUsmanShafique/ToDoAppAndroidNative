package com.example.to_do_list_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter class for RecyclerView
class RecyclerViewAdapter(
    private val toDoList: List<ToDoItemViewModel>,
    private val isCompletedTaskList: Boolean
) : RecyclerView.Adapter<RecyclerViewAdapter.ToDoViewHolder>() {

    // ViewHolder class to hold references to the item views
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val toDoTitle: TextView = itemView.findViewById(R.id.todo_title) // Title TextView
        val toDoSubtitle: TextView = itemView.findViewById(R.id.todo_subtitle) // Subtitle TextView
    }

    // Inflates the item layout and returns a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val itemView: View
        if (isCompletedTaskList) {
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_completed_task_list, parent, false)  // Corrected layout name
        } else {
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.to_do_item_layout, parent, false)



        }
        return ToDoViewHolder(itemView)
    }

    // Binds the data to the views for each item
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentItem = toDoList[position]
        holder.toDoTitle.text = currentItem.title // Set title
        holder.toDoSubtitle.text = currentItem.subTitle // Set subtitle
    }

    // Returns the total number of items in the list
    override fun getItemCount(): Int {
        return toDoList.size
    }
}
