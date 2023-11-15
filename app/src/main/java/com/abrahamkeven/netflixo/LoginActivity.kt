package com.abrahamkeven.netflixo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abrahamkeven.netflixo.ui.theme.NetflixoTheme

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val forgot = findViewById<TextView>(R.id.forgotTextView) //esqueci a senha
        val entrar = findViewById<Button>(R.id.loginButton)

        forgot.setOnClickListener {
            irParaForget()
        }

        entrar.setOnClickListener {
            irParaProfile()
        }
    }

    private fun irParaProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun irParaForget() {
        val intent = Intent(this, ForgetActivity::class.java)
        startActivity(intent)
    }
}