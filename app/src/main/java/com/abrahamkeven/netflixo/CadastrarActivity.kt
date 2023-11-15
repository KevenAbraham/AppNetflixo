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
    private lateinit var nameEditText: EditText //nome
    private lateinit var emailEditText: EditText //email
    private lateinit var passwordEditText: EditText //senha
    private lateinit var confirmPasswordEditText: EditText //confirmação

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        nameEditText = findViewById(R.id.nameEditText)
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
        val name = nameEditText.text.toString().trim()

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

        if (name.length <= 1) {
            Toast.makeText(this, "Digite um nome válido", Toast.LENGTH_SHORT).show()
            return false
        }

        val firstName = name.split(" ")[0] //armazenando o primeiro nome do usuário na variável
        Toast.makeText(this, "Primeiro nome: $firstName", Toast.LENGTH_SHORT).show()

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
