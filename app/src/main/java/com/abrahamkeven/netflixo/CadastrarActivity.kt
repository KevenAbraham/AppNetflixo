package com.abrahamkeven.netflixo

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.abrahamkeven.netflixo.api.UserService
import com.abrahamkeven.netflixo.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//POSSÍVEIS ERROS:
//DUPLA BARRA NA HORA DE PASSA A API
//CRIAÇÃO DA API
//REACT DANDO ERRO DE CRIAÇÃO POIS UM NOVO CAMPO FOI ADICIONADO
//NAO ESTA CADASTRANDO OS DADOS AQUI NO KOTLIN
//FALTA A CRIAÇÃO DOS LINKS

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
                val nome = nameEditText.text.toString().trim()
                val email = emailEditText.text.toString().trim()
                val senha = passwordEditText.text.toString().trim()

                val user = User(nome, email, senha)
                registerUser(user)
            }
        }

        linkToLogin.setOnClickListener {
            entrarLogin()
        }
    }

    private fun validateCredentials(): Boolean {
        val nome = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val senha = passwordEditText.text.toString().trim()
        val confirmPassword = confirmPasswordEditText.text.toString().trim()

        if (nome.length <= 1) {
            Toast.makeText(this, "Digite um nome válido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Insira um email válido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (senha.length <= 8) {
            Toast.makeText(this, "A senha deve ter pelo menos 8 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }

        if (senha != confirmPassword) {
            Toast.makeText(this, "As senhas não correspondem", Toast.LENGTH_SHORT).show()
            return false
        }

        //val firstName = name.split(" ")[0] //armazenando o primeiro nome do usuário na variável
        //Toast.makeText(this, "Primeiro nome: $firstName", Toast.LENGTH_SHORT).show()

        return true
    }

    //to usando essa função ou nao?
    private fun entrarLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun registerUser(user: User) {
        // Criar um objeto Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://web-ek0w5pnhkp3k.up-de-fra1-1.apps.run-on-seenode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Criar o serviço da API
        val userService = retrofit.create(UserService::class.java)

        // Fazer a chamada da API para registrar o usuário
        val call = userService.createUser(user)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    // Sucesso ao cadastrar o usuário
                    Toast.makeText(
                        this@CadastrarActivity,
                        "Usuário cadastrado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Redirecione para a próxima tela ou realize alguma ação
                    val intent = Intent(this@CadastrarActivity, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val errorBody = response.errorBody()?.string()
                    // Mostrar mensagem de erro específica
                    Toast.makeText(this@CadastrarActivity, "Erro ao cadastrar: $errorBody", Toast.LENGTH_SHORT).show()
                    // Log do erro para depuração
                    Log.e("ErrorOnResponse", "Erro ao cadastrar: $errorBody")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // Tratar falha na requisição
                Toast.makeText(this@CadastrarActivity, "Falha na requisição", Toast.LENGTH_SHORT)
                    .show()
                // Realize ações apropriadas para lidar com a falha
            }
        })
    }
}
