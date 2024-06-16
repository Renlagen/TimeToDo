package com.example.timetodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.game.Tasks
import com.example.game.User

class main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main)
        val userName = findViewById<TextView>(R.id.textView15);
        userName.text = User.globalName + "!";
        val db = Tasks(this, null)
        val spin1 = findViewById<Spinner>(R.id.spinner)
        val adapter1: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
            db.getTasksFromMe(User.globalName))
        spin1.adapter = adapter1;
    }

    fun goTask2 (view: View)
    {
        val intent = Intent(this, task_information::class.java);
        startActivity(intent);
    }

    fun goDelete (view: View)
    {
        val intent = Intent(this, delete::class.java);
        startActivity(intent);
    }

    fun goTasks (view: View)
    {
        val intent = Intent(this, find::class.java);
        startActivity(intent);
    }

    fun goAwards (view: View)
    {
        val intent = Intent(this, awards::class.java);
        startActivity(intent);
    }

    fun goChange (view: View)
    {
        val intent = Intent(this, remarke::class.java);
        startActivity(intent);
    }
}