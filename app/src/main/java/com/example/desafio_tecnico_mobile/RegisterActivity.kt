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

class RegisterActivity : AppCompatActivity() {

    private lateinit var editUsername: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editConfirmPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var txtClickToLogin: TextView
    private lateinit var eyeRegisterPassword: ImageView
    private lateinit var eyeConfirm: ImageView

    private var isPasswordVisible = false
    private var isConfirmVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Ajuste de padding para sistema de barras
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializando campos
        editUsername = findViewById(R.id.editTextText3)
        editEmail = findViewById(R.id.editTextText4)
        editPassword = findViewById(R.id.editTextText5)
        editConfirmPassword = findViewById(R.id.editTextText6)
        btnRegister = findViewById(R.id.button3)
        txtClickToLogin = findViewById(R.id.clicktologin)
        eyeRegisterPassword = findViewById(R.id.eyeregisterpassword)
        eyeConfirm = findViewById(R.id.eyeconfirm)

        // Listener do botão de cadastro
        btnRegister.setOnClickListener {
            val username = editUsername.text.toString().trim()
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()
            val confirmPassword = editConfirmPassword.text.toString().trim()

            // Validações
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Digite um e-mail válido", Toast.LENGTH_SHORT).show()
            } else if (password.length < 6) {
                Toast.makeText(this, "A senha deve ter no mínimo 6 caracteres", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show()
            } else {
                val sharedPrefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

                // Verifica se o email já está cadastrado
                if (sharedPrefs.contains(email)) {
                    Toast.makeText(this, "Usuário já cadastrado com este e-mail", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Verifica se o nome de usuário já está cadastrado
                val allEntries = sharedPrefs.all
                if (allEntries.values.any { it == username }) {
                    Toast.makeText(this, "Nome de usuário já está em uso", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Salva no SharedPreferences
                val editor = sharedPrefs.edit()
                editor.putString("username_$email", username) // chave única para nome
                editor.putString(email, password)              // email como chave para senha
                editor.apply()

                Toast.makeText(this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show()

                // Voltar para LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Listener do TextView "Login"
        txtClickToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Fecha o RegisterActivity para não acumular na pilha
        }

        // 🔹 Toggle da senha principal
        eyeRegisterPassword.setOnClickListener {
            if (isPasswordVisible) {
                editPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                isPasswordVisible = false
            } else {
                editPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                isPasswordVisible = true
            }
            editPassword.setSelection(editPassword.text.length)
        }

        // 🔹 Toggle da confirmação de senha
        eyeConfirm.setOnClickListener {
            if (isConfirmVisible) {
                editConfirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                isConfirmVisible = false
            } else {
                editConfirmPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                isConfirmVisible = true
            }
            editConfirmPassword.setSelection(editConfirmPassword.text.length)
        }
    }
}
