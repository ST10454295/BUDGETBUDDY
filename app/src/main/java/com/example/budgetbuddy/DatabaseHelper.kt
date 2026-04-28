package com.example.budgetbuddy

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "BudgetDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            "CREATE TABLE expenses (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "amount TEXT," +
                    "description TEXT," +
                    "date TEXT," +
                    "startTime TEXT," +
                    "endTime TEXT," +
                    "category TEXT," +
                    "image TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS expenses")
        onCreate(db)
    }

    // ✅ INSERT EXPENSE
    fun insertExpense(
        amount: String,
        description: String,
        date: String,
        startTime: String,
        endTime: String,
        category: String,
        image: String?
    ): Boolean {

        val db = this.writableDatabase
        val values = ContentValues()

        values.put("amount", amount)
        values.put("description", description)
        values.put("date", date)
        values.put("startTime", startTime)
        values.put("endTime", endTime)
        values.put("category", category)
        values.put("image", image)

        val result = db.insert("expenses", null, values)
        return result != -1L
    }

    // ✅ GET ALL EXPENSES (THIS FIXES YOUR VIEW SCREEN)
    fun getAllExpenses(): ArrayList<ExpenseModel> {

        val list = ArrayList<ExpenseModel>()
        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM expenses", null)

        if (cursor.moveToFirst()) {
            do {
                val expense = ExpenseModel(
                    id = cursor.getString(0),
                    amount = cursor.getString(1),
                    description = cursor.getString(2),
                    date = cursor.getString(3),
                    startTime = cursor.getString(4),
                    endTime = cursor.getString(5),
                    category = cursor.getString(6),
                    image = cursor.getString(7)
                )

                list.add(expense)

            } while (cursor.moveToNext())
        }

        cursor.close()
        return list
    }
}