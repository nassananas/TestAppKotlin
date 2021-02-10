package com.example.testapplication.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.text.Selection
import java.io.Serializable

class MyDbManager(context: Context) : Serializable {
    val MyDbHelper = com.example.testapplication.db.MyDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = MyDbHelper.writableDatabase
    }

    fun insertToDb(first_name: String, middle_name: String, last_name: String){
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_FIRST_NAME, first_name)
            put(MyDbNameClass.COLUMN_MIDDLE_NAME, middle_name)
            put(MyDbNameClass.COLUMN_LAST_NAME, last_name)
        }

        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }

    
    fun readData() : ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null, null, null, null, null)

        with(cursor){
            while (this?.moveToNext()!!){
                val dataText = cursor?.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_FIRST_NAME)).toString() + " " +
                        cursor?.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_MIDDLE_NAME)).toString() + " " +
                        cursor?.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_LAST_NAME)).toString()
                dataList.add(dataText)
            }
        }
        cursor?.close()
        return dataList
    }

    fun updateData(first_name: String, middle_name: String, last_name: String, array : Array<String>) {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_FIRST_NAME, first_name)
            put(MyDbNameClass.COLUMN_MIDDLE_NAME, middle_name)
            put(MyDbNameClass.COLUMN_LAST_NAME, last_name)
        }
        db?.update(MyDbNameClass.TABLE_NAME, values, "${BaseColumns._ID}=?", array);
    }
    fun closeDb() {
        MyDbHelper.close()
    }

}