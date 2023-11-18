package com.abrahamkeven.netflixo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abrahamkeven.netflixo.api.LoginData
import com.abrahamkeven.netflixo.api.LoginResponse
import com.abrahamkeven.netflixo.api.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText //email
    private lateinit var passwordEditText: EditText //senha

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        val forgot = findViewById<TextView>(R.id.forgotTextView) //esqueci a senha
        val entrar = findViewById<Button>(R.id.loginButton)

        forgot.setOnClickListener {
            irParaForget()
        }

        entrar.setOnClickListener {
            irParaProfile()
        }
    }

    private fun irParaForget() {
        val intent = Intent(this, ForgetActivity::class.java)
        startActivity(intent)
    }
    private fun irParaProfile() {
        val email = emailEditText.text.toString().trim()
        val senha = passwordEditText.text.toString().trim()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://web-ek0w5pnhkp3k.up-de-fra1-1.apps.run-on-seenode.com") // URL do seu backend
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userService = retrofit.create(UserService::class.java)
        val call = userService.loginUser(LoginData(email, senha))

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    // Login bem-sucedido, vá para a tela de perfil
                    val intent = Intent(this@LoginActivity, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Exiba uma mensagem de erro para o usuário
                    Toast.makeText(this@LoginActivity, "Falha no login", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Exiba uma mensagem de erro para o usuário
                Toast.makeText(this@LoginActivity, "Erro na requisição", Toast.LENGTH_SHORT).show()
            }
        })

    }
}