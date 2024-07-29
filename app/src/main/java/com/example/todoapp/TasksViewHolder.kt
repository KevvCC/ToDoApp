package com.example.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TasksViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        private val cbTask: CheckBox = view.findViewById(R.id.cbTask)
        private val tvTask: TextView = view.findViewById(R.id.tvTask)

    fun render(task: Task){
        tvTask.text = task.title
        cbTask.isChecked = task.isSelected

        if (task.isSelected){
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        val color  = when (task.category){
            TasksCategories.Personal -> R.color.md_theme_primary
            TasksCategories.Trabajo -> R.color.md_theme_inversePrimary_mediumContrast
            TasksCategories.Otros ->  R.color.md_theme_tertiary
        }
        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbTask.context, color)
        )
    }
}