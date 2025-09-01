package com.example.desafio_tecnico_mobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnCreateAccount: Button
    private lateinit var txtForgotPassword: TextView
    private lateinit var eyeLoginPassword: ImageView

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editEmail = findViewById(R.id.editTextEmail)
        editPassword = findViewById(R.id.editTextpassword)
        btnLogin = findViewById(R.id.buttonLogin)
        btnCreateAccount = findViewById(R.id.buttonRegister)
        txtForgotPassword = findViewById(R.id.forgotpassword)
        eyeLoginPassword = findViewById(R.id.eyeloginpassword)

        // Verificação ao clicar no botão de login
        btnLogin.setOnClickListener {
            val input = editEmail.text.toString().trim() // pode ser email ou username
            val password = editPassword.text.toString().trim()

            if (input.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                val sharedPrefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                var email: String? = null


                if (sharedPrefs.contains(input)) {
                    email = input
                } else {
                    val allEntries = sharedPrefs.all
                    for ((key, value) in allEntries) {
                        if (key.startsWith("username_") && value == input) {
                            email = key.removePrefix("username_")
                            break
                        }
                    }
                }

                if (email == null) {
                    Toast.makeText(this, "Email ou nome de usuário incorretos", Toast.LENGTH_SHORT).show()
                } else {
                    val savedPassword = sharedPrefs.getString(email, null)
                    if (savedPassword == password) {
                        sharedPrefs.edit().putString("active_user", email).apply()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("USER_EMAIL", email)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Botão "Criar uma conta"
        btnCreateAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Texto "Esqueci minha senha"
        txtForgotPassword.setOnClickListener {
            Toast.makeText(this, "Ainda estamos implementando esta funcionalidade", Toast.LENGTH_SHORT).show()
        }

        // Toggle visibilidade da senha
        eyeLoginPassword.setOnClickListener {
            if (isPasswordVisible) {
                editPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                isPasswordVisible = false
            } else {
                editPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                isPasswordVisible = true
            }
            editPassword.setSelection(editPassword.text.length)
        }
    }
}
