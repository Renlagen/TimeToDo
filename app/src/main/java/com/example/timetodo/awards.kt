package com.example.timetodo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.game.Dates
import com.example.game.Tasks
import com.example.game.User

class awards : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.awards)
        val db = Tasks(this, null)
        val text1 = findViewById<TextView>(R.id.textView13);
        text1.setText(db.getTasksSize(User.globalName).toString());

        var place1 = 0;
        var name1 = "anon";
        var place2 = 0;
        var name2 = "anon";
        var place3 = 0;
        var name3 = "anon";
        val db2 = Dates(this, null)
        val users = db2.getUsers()
        for(name in users)
        {
            val count = db.getTasksSize(name)
            if (count > place1)
            {
                place3 = place2;
                name3 = name2;
                place2 = place1;
                name2 = name1;
                place1 = count;
                name1 = name;
            }
            else if (count > place2)
            {
                place3 = place2;
                name3 = name2;
                place2 = count;
                name2 = name;
            }
            else if (count > place3)
            {
                place3 = place2;
                name3 = name2;
            }
        }
        val text2 = findViewById<TextView>(R.id.textView32);
        val text3 = findViewById<TextView>(R.id.textView33);
        val text4 = findViewById<TextView>(R.id.textView34);
        text2.setText(name1)
        text3.setText(name2)
        text4.setText(name3)
        val text5 = findViewById<TextView>(R.id.textView35);
        val text6 = findViewById<TextView>(R.id.textView36);
        val text7 = findViewById<TextView>(R.id.textView37);
        text5.setText(place1.toString())
        text6.setText(place2.toString())
        text7.setText(place3.toString())
    }

    fun goBack4(view: View) {
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
}