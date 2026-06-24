package com.example.budgetbuddy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewExpensesActivity : AppCompatActivity() {

    lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_view_expense)

        dbHelper = DatabaseHelper(this)

        val recycler = findViewById<RecyclerView>(R.id.recyclerExpenses)

        val data = dbHelper.getAllExpenses()

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = ExpenseAdapter(data)
    }
}