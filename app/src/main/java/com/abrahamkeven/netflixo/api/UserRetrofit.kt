package com.abrahamkeven.netflixo.api

import com.abrahamkeven.netflixo.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/logins") // Substitua com o endpoint para criar um novo usuário na sua API
    fun createUser(@Body userData: User): Call<User>

    @POST("/logins") // Endpoint de login no seu backend
    fun loginUser(@Body loginData: LoginData): Call<LoginResponse>
}

data class LoginData(
    val email: String,
    val senha: String
)

data class LoginResponse(
    // Defina os campos conforme a resposta do seu backend para o login
    val token: String, // Exemplo de um token JWT retornado após o login
    val mensagem: String // Mensagem de sucesso ou erro
)