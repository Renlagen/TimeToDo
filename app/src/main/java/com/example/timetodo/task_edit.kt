package com.example.timetodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.game.Dates
import com.example.game.Task
import com.example.game.Tasks
import com.example.game.User

class task_edit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.task_edit)
        val db = Tasks(this, null)
        val task = db.getTask(Task.globalTaskName, User.globalName)
        val text1 = findViewById<TextView>(R.id.textView24);
        val text2 = findViewById<EditText>(R.id.editTextText6);
        val text3 = findViewById<EditText>(R.id.editTextText);
        text1.setText(task[2]);
        text2.setText(task[4]);
        text3.setText(task[7]);

        val dbb = Dates(this, null)
        val spin1 = findViewById<Spinner>(R.id.spinner2)
        val adapter1: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
            dbb.getUsers())
        spin1.adapter = adapter1;
        spin1.setSelection(dbb.getUsers().indexOf(task[1]))
        val spin2 = findViewById<Spinner>(R.id.spinner3)
        val adapter2: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
            arrayListOf("низкий", "средний", "высокий"))
        spin2.adapter = adapter2;
        spin2.setSelection(arrayListOf("низкий", "средний", "высокий").indexOf(task[3]))
    }

    fun goBack7(view: View) {
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

    fun doChange(view: View)
    {
        val text1 = findViewById<TextView>(R.id.textView24);
        val text2 = findViewById<EditText>(R.id.editTextText6);
        val text3 = findViewById<EditText>(R.id.editTextText);
        val name = text1.text.toString().trim();
        val description = text2.text.toString().trim();
        val tag = text3.text.toString().trim();

        val spin1 = findViewById<Spinner>(R.id.spinner2)
        val spin2 = findViewById<Spinner>(R.id.spinner3)
        val userNameTo = spin1.getSelectedItem().toString();
        val priority = spin2.getSelectedItem().toString();

        val db = Tasks (this, null)
        db.changeTask(User.globalName, userNameTo, name, priority, description, tag)

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