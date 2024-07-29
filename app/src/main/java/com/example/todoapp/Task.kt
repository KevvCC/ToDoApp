package com.example.todoapp

data class Task(val title: String, val category: TasksCategories, var isSelected: Boolean = false) {
}