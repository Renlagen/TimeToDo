package com.example.timetodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.game.Tasks
import com.example.game.User

class delete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.delete)

        val db = Tasks(this, null)
        val spin1 = findViewById<Spinner>(R.id.spinner2)
        val adapter1: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
            db.getTasksFromMe(User.globalName))
        spin1.adapter = adapter1;
    }

    fun goBack3(view: View) {
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

    fun doDelete(view: View)
    {
        val spin1 = findViewById<Spinner>(R.id.spinner2)
        val db = Tasks (this, null)
        db.deleteTask(spin1.getSelectedItem().toString(), User.globalName)
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
}