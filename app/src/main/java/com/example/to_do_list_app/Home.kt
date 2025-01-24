package com.example.to_do_list_app

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
            ToDoItemViewModel("Create a REST API", "Learn how to build a server-side RESTful API", true),
            ToDoItemViewModel("Contribute to Open Source", "Participate in open-source projects on GitHub", false),
            ToDoItemViewModel("Study Data Structures", "Understand and implement basic data structures like arrays and trees", true),
            ToDoItemViewModel("Master Cloud Services", "Learn how to use AWS, GCP, or Azure for cloud computing", false),
            ToDoItemViewModel("UI/UX Design Principles", "Understand the fundamentals of user interface and user experience design", true),
            ToDoItemViewModel("Create a Mobile Game", "Design and develop a mobile game for iOS or Android", true),
            ToDoItemViewModel("Develop a Chatbot", "Build an AI-powered chatbot for a website or app", false),
            ToDoItemViewModel("Advanced Python", "Learn advanced Python topics like decorators, generators, and threading", true),
            ToDoItemViewModel("Blockchain Basics", "Understand how blockchain technology works and its use cases", false),
            ToDoItemViewModel("Cybersecurity Fundamentals", "Learn about securing applications and networks", true),
            ToDoItemViewModel("Automation with Python", "Automate daily tasks with Python scripts", true),
            ToDoItemViewModel("Create a Mobile App from Scratch", "Build a complete mobile app using Android or iOS", false),
            ToDoItemViewModel("Develop a Web App with React", "Learn how to build web apps using React.js", true),
            ToDoItemViewModel("Learn Firebase", "Integrate Firebase into your apps for real-time databases and authentication", true),
            ToDoItemViewModel("Study Software Testing", "Learn how to write tests and test software applications effectively", false)
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

        val addTaskBtn = view.findViewById<FloatingActionButton>(R.id.add_task_btn)
        addTaskBtn.setOnClickListener {
            val intent = Intent(requireContext(), AddTask::class.java) // Use requireContext()
            startActivity(intent)
        }


        return view
    }
}