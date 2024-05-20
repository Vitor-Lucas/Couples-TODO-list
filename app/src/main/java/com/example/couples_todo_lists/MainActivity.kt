package com.example.couples_todo_lists

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.database


class MainActivity : AppCompatActivity() {

    lateinit var createTask_button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // paleta de cores
        // https://coolors.co/palette/210b2c-55286f-bc96e6-d8b4e2-ae759f

        createTask_button = findViewById(R.id.CreateTaskButton)

        val database = Firebase.database

        val table = findViewById<TableLayout>(R.id.tabela)
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


        createTask_button.setOnClickListener {
            val intent = Intent(this, CriarTask::class.java)
            startActivity(intent)
        }

    }
}