package com.example.desafio_tecnico_mobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPrefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val activeUser = sharedPrefs.getString("active_user", null)

            if (activeUser != null) {
                // Já existe um usuário logado → vai direto para MainActivity
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USER_EMAIL", activeUser)
                startActivity(intent)
            } else {
                // Nenhum usuário logado → vai para LoginActivity
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 2000) // tempo da splash (2 segundos)
    }
}
