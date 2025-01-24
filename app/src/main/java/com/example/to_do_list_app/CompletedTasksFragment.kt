package com.example.to_do_list_app

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CompletedTasksFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_completed_tasks, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.completed_tasks_rec_view)

        // Set LayoutManager for RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Use requireContext()
        val toDoList = listOf(
            ToDoItemViewModel("Learn Kotlin", "Master Kotlin for Android development", true),
            ToDoItemViewModel("Build a Portfolio Website", "Create a personal website showcasing your projects", false),
            ToDoItemViewModel("Game Development Fundamentals", "Study basic game development concepts", true),
            ToDoItemViewModel("Explore Machine Learning", "Dive into machine learning basics and algorithms", false),
            ToDoItemViewModel("Develop Cross-Platform App", "Build an app with Flutter or React Native", true),
            ToDoItemViewModel("Read JavaScript Book", "Understand advanced JavaScript concepts for web development hgvvghvghvghvhgvghvhgvhgvgs", false),
            ToDoItemViewModel("Develop Cross-Platform App", "Build an app with Flutter or React Native", true),
        )
        val completedTasks = toDoList.filter{it.isCompleted}
        val adapter = RecyclerViewAdapter(completedTasks, true)
        recyclerView.adapter = adapter

        val backBtn = view.findViewById<ImageView>(R.id.back_btn)
        backBtn.setOnClickListener{
            val intent = Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)

        }




        return view
    }
}