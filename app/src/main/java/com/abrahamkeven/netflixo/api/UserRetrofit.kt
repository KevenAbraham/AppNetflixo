package com.abrahamkeven.netflixo.api

import com.abrahamkeven.netflixo.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/logins") // Substitua com o endpoint para criar um novo usuário na sua API
    fun createUser(@Body userData: User): Call<User>
}