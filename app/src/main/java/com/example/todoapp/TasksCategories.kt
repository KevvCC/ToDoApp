package com.example.todoapp

sealed class TasksCategories(var isChecked: Boolean = true) {
    object Trabajo : TasksCategories()
    object Personal : TasksCategories()
    object Otros : TasksCategories()
}
