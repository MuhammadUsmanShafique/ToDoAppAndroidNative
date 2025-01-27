package com.example.to_do_list_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class EditTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_task)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val task_ID = intent.getIntExtra("id",-1)
        val title = intent.getStringExtra("title")
        val subTitle = intent.getStringExtra("subTitle")
        val isCompleted = intent.getBooleanExtra("isCompleted",false)

        val titleField = findViewById<EditText>(R.id.title)
        val subTitleField = findViewById<EditText>(R.id.details)
        val updateBtn = findViewById<Button>(R.id.updateTask)
        val cancelBtn  = findViewById<Button>(R.id.cancel_edit)
        val backBtn = findViewById<ImageView>(R.id.back_btn)



        titleField.setText(title)
        subTitleField.setText(subTitle)


        fun navigateToHome(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        backBtn.setOnClickListener{
       navigateToHome()
        }
        cancelBtn.setOnClickListener{
       navigateToHome()
        }

        updateBtn.setOnClickListener{


            lifecycleScope.launch {
                val db = ToDoDatabase.getDatabase(this@EditTask)


                val todoDao = db.todoListDao()

                val changedTask = ToDoList(task_ID,titleField.text.toString(),subTitleField.text.toString(),isCompleted,)

                todoDao.editTask(changedTask)

                navigateToHome()

            }



            }









    }
}