package com.abrahamkeven.netflixo.api

import com.abrahamkeven.netflixo.model.Video
import retrofit2.Call
import retrofit2.http.GET

interface VideoService {
    @GET("/links") // Substitua com o endpoint correto da sua API para obter os vídeos
    fun getVideos(): Call<List<Video>> // Aqui é definido o método para obter os vídeos
}