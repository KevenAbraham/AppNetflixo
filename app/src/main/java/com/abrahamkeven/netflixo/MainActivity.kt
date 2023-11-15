package com.abrahamkeven.netflixo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.abrahamkeven.netflixo.ui.theme.NetflixoTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnEntrar = findViewById<Button>(R.id.enterButton)
        val btnCadastrar = findViewById<Button>(R.id.signUpButton)

        btnEntrar.setOnClickListener {
            entrarLogin()
        }

        btnCadastrar.setOnClickListener {
            entrarCadastro()
        }
    }

    private fun entrarLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun entrarCadastro() {
        val intent = Intent(this, CadastrarActivity::class.java)
        startActivity(intent)
        finish()
    }
}