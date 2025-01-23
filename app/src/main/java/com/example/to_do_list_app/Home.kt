package com.example.to_do_list_app

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Home : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.to_do_list_recycler_view)

        val toDoList = listOf(
            ToDoItemViewModel("Learn Kotlin", "Master Kotlin for Android development", true),
            ToDoItemViewModel("Build a Portfolio Website", "Create a personal website showcasing your projects", false),
            ToDoItemViewModel("Game Development Fundamentals", "Study basic game development concepts", true),
            ToDoItemViewModel("Explore Machine Learning", "Dive into machine learning basics and algorithms", false),
            ToDoItemViewModel("Develop Cross-Platform App", "Build an app with Flutter or React Native", true),
            ToDoItemViewModel("Read JavaScript Book", "Understand advanced JavaScript concepts for web development hgvvghvghvghvhgvghvhgvhgvgs", false),
            ToDoItemViewModel("Develop Cross-Platform App", "Build an app with Flutter or React Native", true),
        )

        // Set LayoutManager for RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Use requireContext()

        val processedToDoList = toDoList.map { item ->
            val updatedSubtitle = if (item.subTitle.length > 50) {
                item.subTitle.take(50) + "..." // Truncate and add "..."
            } else {
                item.subTitle // Keep the original subtitle
            }
            item.copy(subTitle = updatedSubtitle) // Create a new item with the updated subtitle
        }

        val adapter = RecyclerViewAdapter(processedToDoList, isCompletedTaskList = false)
        recyclerView.adapter = adapter

        val addTaskBtn = view.findViewById<Button>(R.id.add_task_btn)
        addTaskBtn.setOnClickListener {
            val intent = Intent(requireContext(), AddTask::class.java) // Use requireContext()
            startActivity(intent)
        }


        return view
    }
}