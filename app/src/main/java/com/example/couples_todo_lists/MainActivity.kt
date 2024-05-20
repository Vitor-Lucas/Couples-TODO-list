package com.example.couples_todo_lists

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class MainActivity : AppCompatActivity() {

    lateinit var createTask_button:Button
    lateinit var database:FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // paleta de cores
        // https://coolors.co/palette/210b2c-55286f-bc96e6-d8b4e2-ae759f

        createTask_button = findViewById(R.id.CreateTaskButton)

        database = Firebase.database






        createTask_button.setOnClickListener {
            val intent = Intent(this, CriarTask::class.java)
            startActivity(intent)
        }

    }

    fun createTable() {
        val table = findViewById<TableLayout>(R.id.tabela)
        val myRef = database.reference
        var values:List<String>
        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue()
                values = value as List<String>
                Log.d(TAG, "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }

        })
        for (i in 0..4) {
            val row = TableRow(this)
            val lp = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
            row.layoutParams = lp
            val textView = TextView(this)
            textView.text = "Name $i"
            val checkbox = CheckBox(this)
            row.addView(textView)
            row.addView(checkbox)
            table.addView(row, i)
        }

    }
}