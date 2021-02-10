package com.example.testapplication

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.db.MyDbManager
import com.example.testapplication.db.MyDbNameClass
import kotlinx.android.synthetic.main.activity_edit_form.*


class EditForm : AppCompatActivity() {
    companion object{
        const val POSITION = "position"
    }

    val myDbManager = MyDbManager(this)
    var pos : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_form)

        myDbManager.openDb()
        val selection = "${BaseColumns._ID} = ?"
        pos = intent.getIntExtra(POSITION, 0) as Int
        val selectionArgs = arrayOf<String>("$pos")
        val c: Cursor = myDbManager.db!!.query(MyDbNameClass.TABLE_NAME, null, selection, selectionArgs, null, null, null)
        if (c.moveToFirst()) {
            editText1.setText(c?.getString(c.getColumnIndex(MyDbNameClass.COLUMN_FIRST_NAME)).toString())
            editText2.setText(c?.getString(c.getColumnIndex(MyDbNameClass.COLUMN_MIDDLE_NAME)).toString())
            editText3.setText(c?.getString(c.getColumnIndex(MyDbNameClass.COLUMN_LAST_NAME)).toString())
        }
    }

    fun onClickSave(view: View){
        if ((editText1.text.toString() != "") && (editText2.text.toString() != "") && (editText3.text.toString() != "")) {

            if (pos > 0) {
                myDbManager.updateData(editText1.text.toString(), editText2.text.toString(), editText3.text.toString(), arrayOf("$pos"))
            } else {
                myDbManager.insertToDb(editText1.text.toString(), editText2.text.toString(), editText3.text.toString())
            }
            val dataList = myDbManager.readData()
            myDbManager.closeDb()
            val intent = Intent(this, MyList::class.java)
            intent.putExtra(MyList.KEY_DB, dataList)
            startActivity(intent)
        }
        else{
            val myToast = Toast.makeText(this, "Не все поля заполнены!", Toast.LENGTH_SHORT)
            myToast.show()
        }
    }
}