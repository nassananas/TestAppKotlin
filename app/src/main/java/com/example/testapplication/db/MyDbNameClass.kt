package com.example.testapplication.db

import android.provider.BaseColumns

object MyDbNameClass{
    const val TABLE_NAME = "my_table"
    const val COLUMN_FIRST_NAME = "first_name"
    const val COLUMN_MIDDLE_NAME = "middle_name"
    const val COLUMN_LAST_NAME = "last_name"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "MyDb.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME ("+
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_FIRST_NAME TEXT, $COLUMN_MIDDLE_NAME TEXT," +
            "$COLUMN_LAST_NAME TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

}