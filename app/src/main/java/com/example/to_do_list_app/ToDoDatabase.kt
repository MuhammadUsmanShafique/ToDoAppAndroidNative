package com.example.to_do_list_app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoList::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun todoListDao(): ToDoListDao

    companion object {
        // Singleton instance initialized lazily when first used
        private var INSTANCE: ToDoDatabase? = null

        // Accessor function for getting the database instance
        fun getDatabase(context: Context): ToDoDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
            }
            return INSTANCE!!
        }
    }
}
