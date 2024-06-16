package com.example.game

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Dates(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY, name TEXT, email TEXT, password TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put("name", user.name)
        values.put("email", user.email)
        values.put("password", user.password)

        val db = this.writableDatabase
        db.insert("users", null, values)

        db.close()

    }

    fun getUser(name: String, password: String): Boolean{
        val db = this.readableDatabase

        val result= db.rawQuery("SELECT * FROM users WHERE name = '$name' AND password = '$password'", null)
        return result.moveToFirst()
    }

    fun GetUserName(name: String) : Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM users WHERE name = '$name'", null)
        return result.moveToFirst()
    }

    fun GetUserEmail(email: String) : Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM users WHERE email = '$email'", null)
        return result.moveToFirst()
    }

    fun getUsers() : ArrayList<String>
    {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM users", null)
        val users: ArrayList<String> = arrayListOf();
        if (result.moveToFirst()) {
            while (!result.isAfterLast()) {
                val name = result.getString(result.getColumnIndexOrThrow("name"));
                users.add(name)
                result.moveToNext();
            }
        }
        return users
    }

}