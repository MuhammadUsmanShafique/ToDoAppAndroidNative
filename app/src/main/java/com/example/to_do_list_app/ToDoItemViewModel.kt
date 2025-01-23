package com.example.to_do_list_app

import java.io.Serializable

data class ToDoItemViewModel(
    val title: String,
    val subTitle: String,
    val isCompleted: Boolean
) : Serializable
