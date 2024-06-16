package com.example.game

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Tasks(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app1", factory, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE tasks (id INT PRIMARY KEY, userNameFrom TEXT, userNameTo TEXT, name TEXT, priority TEXT, description TEXT, date TEXT, status TEXT, tag TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS tasks")
        onCreate(db)
    }

    fun addTask(task: Task) {
        val values = ContentValues()
        values.put("userNameFrom", task.userNameFrom)
        values.put("userNameTo", task.userNameTo)
        values.put("name", task.name)
        values.put("priority", task.priority)
        values.put("description", task.description)
        values.put("date", task.date)
        values.put("status", task.status)
        values.put("tag", task.tag)

        val db = this.writableDatabase
        db.insert("tasks", null, values)

        db.close()

    }

    fun getLenght(userNameFrom: String) : Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM tasks WHERE userNameFrom = '$userNameFrom'", null)
        return result.moveToFirst()
    }

    fun getTasksFromMe(userNameFrom: String): ArrayList<String>
    {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM tasks WHERE userNameFrom = '$userNameFrom' AND status == 'active'", null)
        val names: ArrayList<String> = arrayListOf();
        if (result.moveToFirst()) {
            while (!result.isAfterLast()) {
                val name = result.getString(result.getColumnIndexOrThrow("name"));
                names.add(name)
                result.moveToNext();
            }
        }
        return names
    }

    fun deleteTask(name: String, userNameFrom: String)
    {
        val db = this.readableDatabase
        db.delete("tasks",  "name = ? AND userNameFrom = ?", arrayOf(name, userNameFrom))
    }

    fun getTask(name: String, userNameFrom: String): Array<String>
    {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM tasks WHERE userNameFrom = '$userNameFrom' AND name = '$name' AND status == 'active'", null)
        var task = arrayOf("");
        if (result.moveToFirst()) {
            while (!result.isAfterLast()) {
                val userNameTo = result.getString(result.getColumnIndexOrThrow("userNameTo"));
                val priority = result.getString(result.getColumnIndexOrThrow("priority"));
                val description = result.getString(result.getColumnIndexOrThrow("description"));
                val date = result.getString(result.getColumnIndexOrThrow("date"));
                val status = result.getString(result.getColumnIndexOrThrow("status"));
                val tag = result.getString(result.getColumnIndexOrThrow("tag"));
                task = arrayOf(userNameFrom, userNameTo, name, priority, description, date, status, tag)
                result.moveToNext();
            }
        }
        return task
    }

    fun getTaskTo(name: String, userNameTo: String): Array<String>
    {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM tasks WHERE userNameTo = '$userNameTo' AND name = '$name' AND status == 'active'", null)
        var task = arrayOf("");
        if (result.moveToFirst()) {
            while (!result.isAfterLast()) {
                val userNameFrom = result.getString(result.getColumnIndexOrThrow("userNameFrom"));
                val priority = result.getString(result.getColumnIndexOrThrow("priority"));
                val description = result.getString(result.getColumnIndexOrThrow("description"));
                val date = result.getString(result.getColumnIndexOrThrow("date"));
                val status = result.getString(result.getColumnIndexOrThrow("status"));
                val tag = result.getString(result.getColumnIndexOrThrow("tag"));
                task = arrayOf(userNameFrom, userNameTo, name, priority, description, date, status, tag)
                result.moveToNext();
            }
        }
        return task
    }

    fun changeTask(userNameFrom: String, userNameTo: String, name: String, priority: String, description: String, tag: String)
    {
        val db = this.readableDatabase

        val changes = ContentValues()
        changes.put("userNameTo", userNameTo)
        changes.put("priority", priority)
        changes.put("description", description)
        changes.put("tag", tag)
        db.update("tasks", changes, "name = ? AND userNameFrom = ?", arrayOf(name, userNameFrom))
    }

    fun changeStatus(userNameTo: String, name: String)
    {
        val db = this.readableDatabase
        val changes = ContentValues()
        changes.put("status", "solve")
        db.update("tasks", changes, "name = ? AND userNameTo = ?", arrayOf(name, userNameTo))
    }

    fun GetTag(tag: String, userNameTo: String) : Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM tasks WHERE tag = '$tag' AND userNameTo = '$userNameTo' AND status == 'active'", null)
        return result.moveToFirst()
    }

    fun GetPriority(priority: String, userNameTo: String) : Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM tasks WHERE priority = '$priority' AND userNameTo = '$userNameTo' AND status == 'active'", null)
        return result.moveToFirst()
    }

    fun GetStatus(userNameTo: String) : Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM tasks WHERE status = 'solve' AND userNameTo = '$userNameTo'", null)
        return result.moveToFirst()
    }

    fun getTasksSize(userNameTo: String): Int
    {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM tasks WHERE userNameTo = '$userNameTo' AND status == 'solve'", null)
        var count = 0;
        if (result.moveToFirst()) {
            while (!result.isAfterLast()) {
                count += 1;
                result.moveToNext();
            }
        }
        return count
    }

    fun getTasksFromMeTag(userNameTo: String, tag: String): ArrayList<String>
    {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM tasks WHERE userNameTo = '$userNameTo' AND tag = '$tag' AND status == 'active'", null)
        val names: ArrayList<String> = arrayListOf();
        if (result.moveToFirst()) {
            while (!result.isAfterLast()) {
                val name = result.getString(result.getColumnIndexOrThrow("name"));
                names.add(name)
                result.moveToNext();
            }
        }
        return names
    }

    fun getTasksFromMePriority(userNameTo: String, priority: String): ArrayList<String>
    {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM tasks WHERE userNameTo = '$userNameTo' AND priority = '$priority' AND status == 'active'", null)
        val names: ArrayList<String> = arrayListOf();
        if (result.moveToFirst()) {
            while (!result.isAfterLast()) {
                val name = result.getString(result.getColumnIndexOrThrow("name"));
                names.add(name)
                result.moveToNext();
            }
        }
        return names
    }

    fun getTasksFromMeStatus(userNameTo: String): ArrayList<String>
    {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM tasks WHERE userNameTo = '$userNameTo' AND status = 'solve'", null)
        val names: ArrayList<String> = arrayListOf();
        if (result.moveToFirst()) {
            while (!result.isAfterLast()) {
                val name = result.getString(result.getColumnIndexOrThrow("name"));
                names.add(name)
                result.moveToNext();
            }
        }
        return names
    }
}