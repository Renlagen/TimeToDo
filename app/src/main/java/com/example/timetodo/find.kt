package com.example.timetodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.game.Task
import com.example.game.Tasks
import com.example.game.User

class find : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.find)
        val spin1 = findViewById<Spinner>(R.id.spinner4)
        val adapter1: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
            arrayListOf("низкий", "средний", "высокий"))
        spin1.adapter = adapter1;

    }

    fun goBack5(view: View) {
        val db = Tasks (this, null)
        if (!db.getLenght(User.globalName))
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

    fun findPriority(view: View)
    {
        val spin1 = findViewById<Spinner>(R.id.spinner4)
        Task.globalTaskValue = spin1.getSelectedItem().toString();
        Task.globalTaskType = "priority"
        val db = Tasks (this, null)
        if (db.GetPriority(Task.globalTaskValue, User.globalName))
        {
            val intent = Intent(this, look::class.java);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Тэг не найден", Toast.LENGTH_LONG).show();
        }
    }

    fun findTag(view: View)
    {
        val text1 = findViewById<EditText>(R.id.editTextText3);
        Task.globalTaskValue = text1.text.toString().trim();
        Task.globalTaskType = "tag"
        val db = Tasks (this, null)
        if (db.GetTag(Task.globalTaskValue, User.globalName))
        {
            val intent = Intent(this, look::class.java);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Тэг не найден", Toast.LENGTH_LONG).show();
        }
    }

    fun findStatus(view: View)
    {
        Task.globalTaskType = "status"
        val db = Tasks (this, null)
        if (db.GetStatus(User.globalName))
        {
            val intent = Intent(this, look::class.java);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Архив не найден", Toast.LENGTH_LONG).show();
        }
    }
}