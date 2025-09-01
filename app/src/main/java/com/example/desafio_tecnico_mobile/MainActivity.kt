package com.example.desafio_tecnico_mobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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

        // Pegando email vindo do LoginActivity
        val userEmail = intent.getStringExtra("USER_EMAIL")

        // Buscando nome salvo no SharedPreferences
        val sharedPrefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val userName = sharedPrefs.getString("username_$userEmail", "Usuário")


        // Pegando referências
        val textUserName = findViewById<TextView>(R.id.nameuser)  // Nome do usuário
        val textUserEmail = findViewById<TextView>(R.id.emailuser) // Email

        // Atualizando interface
        textUserName.text = userName
        textUserEmail.text = userEmail

        // Botão de Deslogar
        val btnLogout = findViewById<Button>(R.id.deslogar)
        btnLogout.setOnClickListener {
            val editor = sharedPrefs.edit()
            editor.remove("active_user") // remove usuário ativo
            editor.apply()

            // Volta para tela de login
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
