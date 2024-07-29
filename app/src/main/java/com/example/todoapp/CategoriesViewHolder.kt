package com.example.todoapp

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvCategoriesNames: TextView = view.findViewById(R.id.tvCategoriesNames)
    private val vDivider: View = view.findViewById(R.id.vDivider)
    private val cbCategories: MaterialCardView = view.findViewById(R.id.cbCategories)

    fun render(tasksCategories: TasksCategories, onItemSelect: (Int) -> Unit){

        val color = if(tasksCategories.isChecked){
            R.color.md_theme_onPrimaryContainer
        }else{
            R.color.md_theme_inverseSurface
        }

        cbCategories.setBackgroundColor(ContextCompat.getColor(cbCategories.context, color))

        itemView.setOnClickListener { onItemSelect(layoutPosition) }

        when (tasksCategories){
            TasksCategories.Personal -> {
                tvCategoriesNames.text = "Personal"
                vDivider.setBackgroundColor(
                    ContextCompat.getColor(vDivider.context, R.color.md_theme_primary)
                )
            }
            TasksCategories.Trabajo -> {
                tvCategoriesNames.text = "Trabajo"
                vDivider.setBackgroundColor(
                    ContextCompat.getColor(vDivider.context, R.color.md_theme_inversePrimary_mediumContrast)
                )
            }
            TasksCategories.Otros -> {
                tvCategoriesNames.text = "Otros"
                vDivider.setBackgroundColor(
                    ContextCompat.getColor(vDivider.context, R.color.md_theme_tertiary)
                )
            }
        }
    }
}