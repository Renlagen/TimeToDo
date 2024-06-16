package com.example.timetodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.game.User

class no_tasks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.no_tasks)
        val userName = findViewById<TextView>(R.id.textView15);
        userName.text = User.globalName + "!";
    }

    fun goTask(view: View)
    {
        val intent = Intent(this, task_information::class.java);
        startActivity(intent);
    }
}