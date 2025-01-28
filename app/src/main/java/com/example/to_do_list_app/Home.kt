package com.example.to_do_list_app

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Home : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.to_do_list_recycler_view)

//        val toDoList = listOf(
//            ToDoItemViewModel("Learn Kotlin", "Master Kotlin for Android development", true),
//            ToDoItemViewModel("Build a Portfolio Website", "Create a personal website showcasing your projects", false),
//            ToDoItemViewModel("Game Development Fundamentals", "Study basic game development concepts", true),
//            ToDoItemViewModel("Explore Machine Learning", "Dive into machine learning basics and algorithms", false),
//            ToDoItemViewModel("Develop Cross-Platform App", "Build an app with Flutter or React Native", true),
//            ToDoItemViewModel("Create a REST API", "Learn how to build a server-side RESTful API", true),
//        )

        lifecycleScope.launch() {
            val db = ToDoDatabase.getDatabase(requireContext())

            // Accessing the DAO
            val todoDao = db.todoListDao()

            val toDoList = todoDao.getAllTasks();




            // Set LayoutManager for RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Use requireContext()

            val processedToDoList = toDoList.map { item ->
                val updatedSubtitle = if (item.taskSubtitle.length > 50) {
                    item.taskSubtitle.take(50) + "..." // Truncate and add "..."
                } else {
                    item.taskSubtitle // Keep the original subtitle
                }
                item.copy(taskSubtitle = updatedSubtitle) // Create a new item with the updated subtitle
            }

            val adapter = RecyclerViewAdapter(processedToDoList, isCompletedTaskList = false , onDeleteTask = :: onDeleteTask,
                onMarkComplete = :: onMarkComplete)
            recyclerView.adapter = adapter

            val addTaskBtn = view.findViewById<FloatingActionButton>(R.id.add_task_btn)
            addTaskBtn.setOnClickListener {
                val intent = Intent(requireContext(), AddTask::class.java) // Use requireContext()
                startActivity(intent)
            }


        }




        return view
    }
    fun onDeleteTask(taskToDelete:ToDoList){
        lifecycleScope.launch {

            val db = ToDoDatabase.getDatabase(requireContext())

            // Accessing the DAO
            val todoDao = db.todoListDao()
            todoDao.deleteTask(taskToDelete)

            withContext(Dispatchers.Main) {
                Toast.makeText(
                    requireContext(),
                    "Task ${taskToDelete.taskTitle} has been deleted sucessfully!",
                    Toast.LENGTH_SHORT
                ).show()

                val fragmentTransaction = parentFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.container,Home())
                fragmentTransaction.addToBackStack(null) // This allows going back to the HomeFragment
                fragmentTransaction.commit()
            }


        }

    }




    fun onMarkComplete(task_id: Int, isCompleted:Boolean) {

        if (!isCompleted) {
            lifecycleScope.launch(Dispatchers.IO) {
                val db = ToDoDatabase.getDatabase(requireContext())
                val todoDao = db.todoListDao()
                todoDao.markAsCompleted(task_id)

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        "Task marked as completed!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        else{
            Toast.makeText(
                requireContext(),
                "Task was already marked as completed!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }





}