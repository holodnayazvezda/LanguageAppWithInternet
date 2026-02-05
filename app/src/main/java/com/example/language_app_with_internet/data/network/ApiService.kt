package com.example.language_app_with_internet.data.network

import com.example.language_app_with_internet.data.network.model.UserDto
import retrofit2.http.GET

interface ApiService {
    @GET("get_top_users")
    suspend fun getTopUsers(): List<UserDto>
}