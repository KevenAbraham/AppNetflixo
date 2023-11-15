package com.abrahamkeven.netflixo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abrahamkeven.netflixo.ui.theme.NetflixoTheme

class CadastrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        val linkToLogin = findViewById<TextView>(R.id.haveAnAccountTextView) //clicar em já tem uma conta
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            irParaPerfil()
        }

        linkToLogin.setOnClickListener {
            entrarLogin()
        }
    }

    private fun entrarLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() //destrói essa sessão atual e abre a nova janela (login)
    }

    private fun irParaPerfil() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }
}