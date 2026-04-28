package com.example.budgetbuddy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ================= BUTTONS =================
        val btnAddCategory = findViewById<Button>(R.id.btnAddCategory)
        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)
        val btnViewExpenses = findViewById<Button>(R.id.btnViewExpenses)
        val btnCategoryTotals = findViewById<Button>(R.id.btnCategoryTotals)

        // Navigate to Add Category
        btnAddCategory.setOnClickListener {
            startActivity(Intent(this, AddCategoryActivity2::class.java))
        }

        // Navigate to Add Expense
        btnAddExpense.setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity2::class.java))
        }

        // Navigate to View Expenses
        btnViewExpenses.setOnClickListener {
            startActivity(Intent(this, ViewExpenseActivity2::class.java))
        }

        // Navigate to Category Totals
        btnCategoryTotals.setOnClickListener {
            startActivity(Intent(this, CategoryTotalsActivity2::class.java))
        }
    }
}