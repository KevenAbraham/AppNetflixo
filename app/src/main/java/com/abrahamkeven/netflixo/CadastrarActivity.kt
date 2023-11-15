package com.abrahamkeven.netflixo

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CadastrarActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)

        val linkToLogin = findViewById<TextView>(R.id.haveAnAccountTextView)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            if (validateCredentials()) {
                irParaPerfil()
            }
        }

        linkToLogin.setOnClickListener {
            entrarLogin()
        }
    }

    private fun validateCredentials(): Boolean {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val confirmPassword = confirmPasswordEditText.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Insira um email válido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 8) {
            Toast.makeText(this, "A senha deve ter pelo menos 8 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "As senhas não correspondem", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun entrarLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun irParaPerfil() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }
}
