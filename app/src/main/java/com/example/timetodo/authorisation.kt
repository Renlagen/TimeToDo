package com.example.timetodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.game.Dates
import com.example.game.Tasks
import com.example.game.User

class authorisation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.authorisation)
    }

    fun goReg(view: View)
    {
        val intent = Intent(this, registration::class.java);
        startActivity(intent);
    }
    fun entryUser(view: View)
    {
        val userName = findViewById<EditText>(R.id.editTextText2);
        val userPassword = findViewById<EditText>(R.id.editTextTextPassword);
        val name = userName.text.toString().trim();
        val password = userPassword.text.toString().trim();
        if (name == "" || password == "")
            Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show();
        else
        {
            val db = Dates(this, null)
            if (db.getUser(name, password))
            {
                User.globalName = name;
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
            else
                Toast.makeText(this, "Неверные данные", Toast.LENGTH_LONG).show();
        }
    }
}