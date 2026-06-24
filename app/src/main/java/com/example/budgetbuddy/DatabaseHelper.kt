package com.example.budgetbuddy

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "BudgetDB", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            """
            CREATE TABLE expenses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                amount REAL,
                description TEXT,
                date TEXT,
                startTime TEXT,
                endTime TEXT,
                category TEXT,
                image TEXT
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS expenses")
        onCreate(db)
    }

    fun insertExpense(
        amount: String,
        description: String,
        date: String,
        startTime: String,
        endTime: String,
        category: String,
        image: String?
    ): Boolean {

        val db = writableDatabase
        val values = ContentValues()

        values.put("amount", amount.toDoubleOrNull() ?: 0.0)
        values.put("description", description)
        values.put("date", date)
        values.put("startTime", startTime)
        values.put("endTime", endTime)
        values.put("category", category)
        values.put("image", image)

        return db.insert("expenses", null, values) != -1L
    }

    fun getAllExpenses(): ArrayList<ExpenseModel> {

        val list = ArrayList<ExpenseModel>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM expenses", null)

        while (cursor.moveToNext()) {

            list.add(
                ExpenseModel(
                    id = cursor.getString(0),
                    amount = cursor.getString(1),
                    description = cursor.getString(2),
                    date = cursor.getString(3),
                    startTime = cursor.getString(4),
                    endTime = cursor.getString(5),
                    category = cursor.getString(6),
                    image = cursor.getString(7)
                )
            )
        }

        cursor.close()
        return list
    }
}