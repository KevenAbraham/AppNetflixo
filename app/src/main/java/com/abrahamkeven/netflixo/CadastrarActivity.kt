package com.abrahamkeven.netflixo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abrahamkeven.netflixo.api.UserService
import com.abrahamkeven.netflixo.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CadastrarActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText //nome
    private lateinit var emailEditText: EditText //email

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)

        val loginButton = findViewById<Button>(R.id.loginButton)
        val imgLogo = findViewById<ImageView>(R.id.logoImageView)

        imgLogo.setOnClickListener {
            val intent = Intent(this@CadastrarActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginButton.setOnClickListener { //Registering the name and the e-mail adress in the database.
            if (validateCredentials()) {
                val nome = nameEditText.text.toString().trim()
                val email = emailEditText.text.toString().trim()

                val user = User(nome, email)
                registerUser(user)
            }
        }
    }

    private fun validateCredentials(): Boolean {
        val nome = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()

        if (nome.length <= 1) {
            Toast.makeText(this, "Digite um nome válido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Insira um email válido", Toast.LENGTH_SHORT).show()
            return false
        }

        //val firstName = nome.split(" ")[0] //armazenando o primeiro nome do usuário na variável
        //Toast.makeText(this, "Primeiro nome: $firstName", Toast.LENGTH_SHORT).show()

        return true
    }

    private fun retornarMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun registerUser(user: User) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://web-ek0w5pnhkp3k.up-de-fra1-1.apps.run-on-seenode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userService = retrofit.create(UserService::class.java)

        val call = userService.createUser(user)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@CadastrarActivity,
                        "Você receberá notificações sobre futuros lançamentos.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@CadastrarActivity, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val errorBody = response.errorBody()?.string()
                    Toast.makeText(this@CadastrarActivity, "Email já cadastrado, escolha outro.", Toast.LENGTH_SHORT).show()
                    Log.e("ErrorOnResponse", "Erro ao cadastrar: $errorBody")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@CadastrarActivity, "Falha na requisição", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
