package com.example.timetodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.game.Dates
import com.example.game.Task
import com.example.game.Tasks
import com.example.game.User

class task_information : AppCompatActivity() {
    var task = Task(User.globalName, "", "", "низкий", "", "", "", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.task_information)
        val db = Dates(this, null)
        val spin1 = findViewById<Spinner>(R.id.spinner2)
        val spin2 = findViewById<Spinner>(R.id.spinner3)
        val adapter1: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
            db.getUsers())
        task.userNameTo = db.getUsers()[0];
        spin1.adapter = adapter1;
        val adapter2: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
            arrayListOf("низкий", "средний", "высокий"))
        spin2.adapter = adapter2;
    }

    fun goBack1(view: View) {
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

    fun goBack2(view: View)
    {
        val text1 = findViewById<EditText>(R.id.editTextText4);
        val text2 = findViewById<EditText>(R.id.editTextText6);
        val text3 = findViewById<EditText>(R.id.editTextText);
        task.name = text1.text.toString().trim();
        task.description = text2.text.toString().trim();
        task.tag = text3.text.toString().trim();

        val spin1 = findViewById<Spinner>(R.id.spinner2)
        val spin2 = findViewById<Spinner>(R.id.spinner3)
        task.userNameTo = spin1.getSelectedItem().toString();
        task.priority = spin2.getSelectedItem().toString();

        task.status = "active"

        val db = Tasks (this, null)
        db.addTask(task)
        val intent = Intent(this, main::class.java);
        startActivity(intent);



    }
}