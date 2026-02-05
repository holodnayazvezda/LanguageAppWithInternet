package com.example.language_app_with_internet.domain.repository

import com.example.language_app_with_internet.domain.models.TopUser

interface TopUsersRepository {
    suspend fun getTopUsers(): List<TopUser>
}