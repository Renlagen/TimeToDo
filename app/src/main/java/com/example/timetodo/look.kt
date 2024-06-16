package com.example.timetodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.game.Task
import com.example.game.Tasks
import com.example.game.User

class look : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.look)

        val db = Tasks(this, null)
        val spin1 = findViewById<Spinner>(R.id.spinner2)
        if (Task.globalTaskType == "tag")
        {
            val adapter1: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                db.getTasksFromMeTag(User.globalName, Task.globalTaskValue))
            spin1.adapter = adapter1;
        }
        else if (Task.globalTaskType == "status")
        {
            val adapter1: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                db.getTasksFromMeStatus(User.globalName))
            spin1.adapter = adapter1;
        }
        else
        {
            val adapter1: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                db.getTasksFromMePriority(User.globalName, Task.globalTaskValue))
            spin1.adapter = adapter1;
        }
    }

    fun goBack8(view: View) {
        val db2 = Tasks (this, null)
        if (!db2.getLenght(User.globalName))
        {
            val intent = Intent(this, no_tasks::class.java);
            startActivity(intent);
        }
        else
        {
            val intent = Intent(this, main::class.java);
            startActivity(intent);
        }
    }

    fun seeTask(view: View)
    {
        val spin1 = findViewById<Spinner>(R.id.spinner2)
        Task.globalTaskName = spin1.getSelectedItem().toString();
        val intent = Intent(this, task_look::class.java);
        startActivity(intent);
    }
}