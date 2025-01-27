package com.example.to_do_list_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AddTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_task)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backBtn = findViewById<ImageView>(R.id.back_btn)
        val addTaskBtn = findViewById<Button>(R.id.add_task_btn)
        val title = findViewById<EditText>(R.id.title)
        val subTitle = findViewById<EditText>(R.id.details)


        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish AddTask activity after navigating back
        }

        addTaskBtn.setOnClickListener {
            val taskTitle = title.text.toString()
            val taskSubtitle = subTitle.text.toString()

            if (taskTitle.isNotEmpty() && taskSubtitle.isNotEmpty()) {
                lifecycleScope.launch {
                    try {
                        // Get the database instance
                        val db = ToDoDatabase.getDatabase(this@AddTask)


                        val task = ToDoList(
                            taskTitle = taskTitle,
                            taskSubtitle = taskSubtitle,
                            isCompleted = false // Assuming the task is not completed initially
                        )
                        db.todoListDao().addTask(task)

                        // Navigate back to the home activity after adding the task
                        val intent = Intent(this@AddTask, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } catch (e: Exception) {
                        // Handle any errors (like database issues) here
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}
