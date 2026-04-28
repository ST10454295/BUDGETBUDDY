package com.example.budgetbuddy

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddExpenseActivity : AppCompatActivity() {

    lateinit var dbHelper: DatabaseHelper

    private val IMAGE_PICK_CODE = 1000
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_add_expense2)

        dbHelper = DatabaseHelper(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSave = findViewById<Button>(R.id.btnSaveExpense)
        val btnAddImage = findViewById<Button>(R.id.btnAddImage)
        val imgPreview = findViewById<ImageView>(R.id.imgPreview)

        // 📸 IMAGE PICKER
        btnAddImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }

        // 💾 SAVE EXPENSE
        btnSave.setOnClickListener {

            val amount = findViewById<EditText>(R.id.etAmount).text.toString()
            val description = findViewById<EditText>(R.id.etDescription).text.toString()
            val date = findViewById<EditText>(R.id.etDate).text.toString()
            val start = findViewById<EditText>(R.id.etStart).text.toString()
            val end = findViewById<EditText>(R.id.etEnd).text.toString()
            val category = findViewById<EditText>(R.id.etCategory).text.toString()

            val success = dbHelper.insertExpense(
                amount,
                description,
                date,
                start,
                end,
                category,
                imageUri?.toString() ?: ""
            )

            if (success) {
                Toast.makeText(this, "Expense saved!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to save expense", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 📸 HANDLE IMAGE RESULT
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {

            imageUri = data?.data

            val imgPreview = findViewById<ImageView>(R.id.imgPreview)
            imgPreview.setImageURI(imageUri)
        }
    }
}