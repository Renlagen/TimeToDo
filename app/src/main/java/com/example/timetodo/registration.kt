package com.example.timetodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.game.Dates
import com.example.game.User

class registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.registration)
    }
    fun goEntry(view: View)
    {
        val intent = Intent(this, authorisation::class.java);
        startActivity(intent);
    }

    fun inputUser(view: View)
    {
        val userName = findViewById<EditText>(R.id.editTextText2);
        val userEmail = findViewById<EditText>(R.id.editTextTextEmailAddress);
        val userPassword = findViewById<EditText>(R.id.editTextTextPassword);
        val name = userName.text.toString().trim();
        val email = userEmail.text.toString().trim();
        val password = userPassword.text.toString().trim();
        if (name == "" || email == "" || password == "")
            Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show();
        else if (password.length < 6)
            Toast.makeText(this, "Пароль не менее 6 символов", Toast.LENGTH_LONG).show();
        else
        {
            val db = Dates (this, null)
            if (db.GetUserName(name))
                Toast.makeText(this, "Такое имя уже есть", Toast.LENGTH_LONG).show();
            else if (db.GetUserEmail(email))
                Toast.makeText(this, "Такая почта уже есть", Toast.LENGTH_LONG).show();
            else {
                userName.text.clear();
                userEmail.text.clear();
                userPassword.text.clear();
                Toast.makeText(this, "Успешная регистрация", Toast.LENGTH_LONG).show();
                val user = User(name, email, password);
                db.addUser(user)
            }
        }
    }
}
