package com.example.testapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.db.MyDbManager
import kotlinx.android.synthetic.main.activity_my_list.*
import java.util.*
import kotlin.collections.ArrayList


class MyList : AppCompatActivity() {
    companion object{
        const val KEY_DB = "key_db"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_list)
        val dataList = intent.getStringArrayListExtra(KEY_DB) as ArrayList<String>

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        lv.setAdapter(arrayAdapter)

        lv.setOnItemClickListener (AdapterView.OnItemClickListener {parent,view,position,id ->
            val selectedItem = parent.getItemAtPosition(position) as String
            val intent = Intent(this, EditForm::class.java)
            intent.putExtra(EditForm.POSITION, position+1)
            startActivity(intent)
        })
    }
    
    fun onClickAdd(view: View) {
        val indent = Intent(this, EditForm::class.java)
        indent.putExtra(EditForm.POSITION, -1)
        startActivity(indent)
    }
    
}