package com.example.to_do_list_app

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

// Adapter class for RecyclerView
class RecyclerViewAdapter(
    private val toDoList: List<ToDoList>,
    private val isCompletedTaskList: Boolean,
    private val onDeleteTask: ((ToDoList) -> Unit)? = null ,// Optional callback (for deleting task on Home)
    private val onMarkComplete:((Int)-> Unit?)? = null
) : RecyclerView.Adapter<RecyclerViewAdapter.ToDoViewHolder>() {

    // ViewHolder class to hold references to the item views
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val toDoTitle: TextView = itemView.findViewById(R.id.todo_title) // Title TextView
        val toDoSubtitle: TextView = itemView.findViewById(R.id.todo_subtitle) // Subtitle TextView
        val editBtn: ImageView? = itemView.findViewById(R.id.edit)
        val deleteBtn:ImageView? = itemView.findViewById(R.id.delete_btn)
        val markCompletedBtn:ImageView? = itemView.findViewById(R.id.make_complete_btn)
    }

    // Inflates the item layout and returns a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val itemView: View
        if (isCompletedTaskList) {
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_completed_task_list, parent, false)
        } else {
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.to_do_item_layout, parent, false)
        }
        return ToDoViewHolder(itemView) // Only pass itemView here
    }

    // Binds the data to the views for each item
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentItem = toDoList[position]
        holder.toDoTitle.text = currentItem.taskTitle // Set title
        holder.toDoSubtitle.text = currentItem.taskSubtitle // Set subtitle

        // Set up the listener for the edit button
        if (!isCompletedTaskList) {
            holder.editBtn?.setOnClickListener {
                val intent = Intent(holder.itemView.context, EditTask::class.java)
                intent.putExtra("title", currentItem.taskTitle)
                intent.putExtra("subTitle", currentItem.taskSubtitle)
                intent.putExtra("id", currentItem.tId)
                intent.putExtra("isCompleted",currentItem.isCompleted)
                holder.itemView.context.startActivity(intent)
            }

            holder.deleteBtn?.setOnClickListener{
                onDeleteTask?.invoke(currentItem)
            }
            holder.markCompletedBtn?.setOnClickListener{
                onMarkComplete?.invoke(currentItem.tId)
            }



        }
    }

    // Returns the total number of items in the list
    override fun getItemCount(): Int {
        return toDoList.size
    }
}








//class RecyclerViewAdapter(
//    private val toDoList: List<ToDoList>,
//    private val isCompletedTaskList: Boolean
//) : RecyclerView.Adapter<RecyclerViewAdapter.ToDoViewHolder>() {
//
//    // ViewHolder class to hold references to the item views
//    class ToDoViewHolder(itemView: View ,isCompletedTaskList: Boolean, currentItem: ToDoList) : RecyclerView.ViewHolder(itemView) {
//        val toDoTitle: TextView = itemView.findViewById(R.id.todo_title) // Title TextView
//        val toDoSubtitle: TextView = itemView.findViewById(R.id.todo_subtitle) // Subtitle TextView
//
//
//
//        val editBtn:ImageView? = itemView.findViewById(R.id.edit)
//
//        init {
//            if (!isCompletedTaskList) {
//                editBtn?.setOnClickListener {
//                    val intent = Intent(itemView.context, EditTask::class.java)
//                    intent.putExtra("title", toDoTitle.text.toString())
//                    intent.putExtra("subTitle", toDoSubtitle.text.toString())
//                    intent.putExtra("id",currentItem.toString())
//                    itemView.context.startActivity(intent)
//                }
//            }
//        }
//    }
//
//    // Inflates the item layout and returns a ViewHolder
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
//        val itemView: View
//        if (isCompletedTaskList) {
//            itemView = LayoutInflater.from(parent.context)
//                .inflate(R.layout.activity_completed_task_list, parent, false)  // Corrected layout name
//        } else {
//            itemView = LayoutInflater.from(parent.context)
//                .inflate(R.layout.to_do_item_layout, parent, false)
//
//        }
//        return ToDoViewHolder(itemView,isCompletedTaskList)
//    }
//
//    // Binds the data to the views for each item
//    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
//        val currentItem = toDoList[position]
//        currentItem.tId
//        holder.toDoTitle.text = currentItem.taskTitle // Set title
//        holder.toDoSubtitle.text = currentItem.taskSubtitle // Set subtitle
//    }
//
//    // Returns the total number of items in the list
//    override fun getItemCount(): Int {
//        return toDoList.size
//    }
//}
