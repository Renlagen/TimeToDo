package com.example.timetodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.game.Task
import com.example.game.Tasks
import com.example.game.User

class task_look : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.task_look)
        val db = Tasks(this, null)
        val task = db.getTaskTo(Task.globalTaskName, User.globalName)
        val text1 = findViewById<TextView>(R.id.textView25);
        val text2 = findViewById<TextView>(R.id.textView24);
        val text3 = findViewById<TextView>(R.id.textView27);
        val text4 = findViewById<TextView>(R.id.textView28);
        val text5 = findViewById<TextView>(R.id.textView29);
        val text6 = findViewById<TextView>(R.id.textView31);
        text1.setText(task[1]);
        text2.setText(task[2]);
        text3.setText(task[3]);
        text4.setText(task[4]);
        text5.setText(task[7]);
        text6.setText("16.06.2024");
    }

    fun goBack9(view: View) {
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

    fun doSolve(view: View)
    {
        val db = Tasks (this, null)
        val task = db.getTaskTo(Task.globalTaskName, User.globalName)
        db.changeStatus(task[1], task[2])
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