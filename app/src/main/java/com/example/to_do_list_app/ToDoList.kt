package com.example.to_do_list_app

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoList(
    @PrimaryKey(autoGenerate = true) val tId: Int = 0,
    @ColumnInfo(name = "task_title") val taskTitle: String,
    @ColumnInfo(name = "task_subtitle") val taskSubtitle: String,
    @ColumnInfo(name = "is_completed") val isCompleted: Boolean = false
)

