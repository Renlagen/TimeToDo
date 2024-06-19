package com.example.timetodo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.game.Dates
import com.example.game.User
import android.util.Patterns

class registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.registration)

        val privacyPolicyTextView: TextView = findViewById<TextView>(R.id.privacyPolicyTextView)
        val spannableString = SpannableString("Я ознакомлен(а) с Политикой конфиденциальности")
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Renlagen/TimeToDo/blob/main/Политика%20конфиденциальности.pdf"))
                startActivity(browserIntent)
            }
        }

        spannableString.setSpan(clickableSpan, 18, spannableString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        privacyPolicyTextView.text = spannableString
        privacyPolicyTextView.movementMethod = LinkMovementMethod.getInstance()
    }
    fun goEntry(view: View)
    {
        val intent = Intent(this, authorisation::class.java);
        startActivity(intent);
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun inputUser(view: View)
    {
        val userName = findViewById<EditText>(R.id.editTextText2);
        val userEmail = findViewById<EditText>(R.id.editTextTextEmailAddress);
        val userPassword = findViewById<EditText>(R.id.editTextTextPassword);
        val privacyPolicyCheckbox: CheckBox = findViewById<CheckBox>(R.id.privacyPolicyCheckbox);



        val name = userName.text.toString().trim();
        val email = userEmail.text.toString().trim();
        val password = userPassword.text.toString().trim();
        val isPrivacyPolicyAccepted = privacyPolicyCheckbox.isChecked;

        if (name == "" || email == "" || password == "")
            Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show();
        else if (password.length < 6)
            Toast.makeText(this, "Пароль не менее 6 символов", Toast.LENGTH_LONG).show();
        else if (!isEmailValid(email))
            Toast.makeText(this, "Некорректный формат email", Toast.LENGTH_LONG).show();
        else {
            val db = Dates(this, null)
            if (db.GetUserName(name))
                Toast.makeText(this, "Такое имя уже есть", Toast.LENGTH_LONG).show();
            else if (db.GetUserEmail(email))
                Toast.makeText(this, "Такая почта уже есть", Toast.LENGTH_LONG).show();
            else if (!isPrivacyPolicyAccepted) {
                Toast.makeText(
                    this,
                    "Вы должны согласиться с политикой конфиденциальности",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                userName.text.clear();
                userEmail.text.clear();
                userPassword.text.clear();
                privacyPolicyCheckbox.isChecked = false

                Toast.makeText(this, "Успешная регистрация", Toast.LENGTH_LONG).show();
                val user = User(name, email, password);
                db.addUser(user)
            }

        }
    }
}
