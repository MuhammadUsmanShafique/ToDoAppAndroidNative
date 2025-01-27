package com.example.to_do_list_app

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDoListDao {

    @Query("SELECT * FROM ToDoList")
    suspend fun getAllTasks(): List<ToDoList>

    @Query("SELECT * FROM ToDoList WHERE is_completed = 1")
    suspend fun getCompletedTasks(): List<ToDoList>

    @Insert
    suspend fun addTask(task:ToDoList)

    @Query("Delete from ToDoList")
    suspend fun deleteAllTasks()


    @Update
   suspend fun editTask(task:ToDoList)

   @Delete
   suspend fun deleteTask(task:ToDoList)

    @Query("UPDATE ToDoList SET is_completed = 1 WHERE tID = :taskID")
    fun markAsCompleted(taskID: Int)


}
