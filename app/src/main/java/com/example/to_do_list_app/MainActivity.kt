package com.example.to_do_list_app
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Get the BottomNavigationView reference
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Set navigation listener
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        // Get the FragmentManager
        fragmentManager = supportFragmentManager

        // Set default fragment (assuming Home fragment)
        val homeFragment = Home()
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, homeFragment)
        transaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.all-> {
                val homeFragment = Home()
                replaceFragment(homeFragment)
                return true
            }
            R.id.completed -> { // Assuming R.id.navigation_completed is your ID for CompletedTasks fragment
                val completedTasksFragment = CompletedTasksFragment()
                replaceFragment(completedTasksFragment)
                return true
            }
        }
        return false
    }

    public fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}






//package com.example.to_do_list_app
//
//import android.os.Bundle
//import android.widget.Button
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.recyclerview.widget.RecyclerView
//import androidx.recyclerview.widget.LinearLayoutManager
//import android.content.Intent
//import android.widget.ImageView
//import android.widget.LinearLayout
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//        val recyclerView = findViewById<RecyclerView>(R.id.to_do_list_recycler_view)
//        val viewCompleted = findViewById<LinearLayout>(R.id.view_completed);
//        val toDoList = listOf(
//            ToDoItemViewModel("Learn Kotlin", "Master Kotlin for Android development", true),
//            ToDoItemViewModel("Build a Portfolio Website", "Create a personal website showcasing your projects", false),
//            ToDoItemViewModel("Game Development Fundamentals", "Study basic game development concepts", true),
//            ToDoItemViewModel("Explore Machine Learning", "Dive into machine learning basics and algorithms", false),
//            ToDoItemViewModel("Develop Cross-Platform App", "Build an app with Flutter or React Native", true),
//            ToDoItemViewModel("Read JavaScript Book", "Understand advanced JavaScript concepts for web development hgvvghvghvghvhgvghvhgvhgvgh     s", false)
//        )
//
//        // Set LayoutManager for RecyclerView
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        val processedToDoList = toDoList.map { item ->
//            val updatedSubtitle = if (item.subTitle.length > 50) {
//                item.subTitle.take(50) + "..." // Truncate and add "..."
//            } else {
//                item.subTitle // Keep the original subtitle
//            }
//            item.copy(subTitle = updatedSubtitle) // Create a new item with the updated subtitle
//        }
//
//      val adapter = RecyclerViewAdapter(processedToDoList,isCompletedTaskList = false)
//       recyclerView.adapter = adapter
//
//val addTaskBtn = findViewById<Button>(R.id.add_task_btn)
// addTaskBtn.setOnClickListener{
//     val intent = Intent(this,AddTask::class.java)
//     startActivity(intent)
// }
//
//  viewCompleted.setOnClickListener{
//      val intent = Intent(this,CompletedTasks::class.java)
//      val completedTasks = toDoList.filter{it.isCompleted}
//      intent.putExtra("completedTasks", ArrayList(completedTasks))
//      startActivity(intent)
//  }
//
//
//
//    }
//
//
//
//
//
//
//
//}





