package com.example.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.testapplication.db.MyDbManager
import kotlinx.android.synthetic.main.activity_edit_form.*

class MainActivity : AppCompatActivity() {
    val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myDbManager.openDb()

        myDbManager.updateData("Гвоздикин", "Антон", "Антонович", arrayOf("8"))
        val dataList = myDbManager.readData()

        val indent = Intent(this, MyList::class.java)
        indent.putExtra(MyList.KEY_DB, dataList)

        Handler().postDelayed({
            startActivity(indent)
            finish()
        }, 3000)
    }

}


