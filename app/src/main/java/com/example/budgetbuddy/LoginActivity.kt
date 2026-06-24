package com.example.budgetbuddy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        val email = findViewById<EditText>(R.id.LoginEmail)
        val password = findViewById<EditText>(R.id.LoginPassword)

        val btnLogin = findViewById<Button>(R.id.btnLogin)

        val txtSignup = findViewById<TextView>(R.id.txtSignup)

        // LOGIN BUTTON
        btnLogin.setOnClickListener {

            val userEmail = email.text.toString().trim()
            val userPassword = password.text.toString().trim()

            if (userEmail.isEmpty() || userPassword.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                // TEMP LOGIN
                Toast.makeText(
                    this,
                    "Login Successful",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(
                    Intent(this, MainActivity::class.java)
                )

                finish()
            }
        }

        // SIGNUP CLICK
        txtSignup.setOnClickListener {

            Toast.makeText(
                this,
                "Signup screen not added yet",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}