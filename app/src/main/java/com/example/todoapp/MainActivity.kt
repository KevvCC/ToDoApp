package com.example.todoapp

import android.app.Dialog
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.TasksCategories.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {

    private lateinit var rvCategories: RecyclerView
    private lateinit var rvTasks: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var fbAddTask: FloatingActionButton

    private val categories = listOf(
        Personal,
        Trabajo,
        Otros
    )

    private val tasks = mutableListOf(
        Task("Añade tus tareas!", Personal)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initComponents()
        initUI()
        initListener()
    }

    private fun initComponents() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fbAddTask = findViewById(R.id.fbAddTask)
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_add_task)

        val btnAddTask: MaterialButton = dialog.findViewById(R.id.btnAddTask)
        val tfAddTask: TextInputLayout = dialog.findViewById(R.id.tfAddTask)
        val etAddTask: TextInputEditText = tfAddTask.editText as TextInputEditText
        val rgTasks: RadioGroup = dialog.findViewById(R.id.rgTasks)

        btnAddTask.setOnClickListener {
            val currentTask = etAddTask.text.toString()
            if (currentTask.isEmpty()) return@setOnClickListener
            val selectedTask = rgTasks.checkedRadioButtonId
            val selectedRadioButton: RadioButton = rgTasks.findViewById(selectedTask)
            val currentCat: TasksCategories = when (selectedRadioButton.text) {
                getString(R.string.personal) -> Personal
                getString(R.string.work) -> Trabajo
                else -> Otros
            }
            tasks.add(Task(currentTask, currentCat))
            updateTask()
            dialog.hide()
        }

        dialog.show()
    }

    private fun initListener() {
        fbAddTask.setOnClickListener { showDialog() }
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories) { position -> updateCat(position) }
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks) { onItemSelect(it) }
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }

    private fun updateCat(position: Int) {
        categories[position].isChecked = !categories[position].isChecked
        categoriesAdapter.notifyDataSetChanged()
        updateTask()
    }

    private fun onItemSelect(position: Int) {
        tasks[position].isSelected = !tasks[position].isSelected
        updateTask()
    }

    private fun updateTask() {
        val selectedCats: List<TasksCategories> = categories.filter { it.isChecked }
        val newTask = tasks.filter { selectedCats.contains(it.category) }
        tasksAdapter.tasks = newTask
        tasksAdapter.notifyDataSetChanged()
    }
}
