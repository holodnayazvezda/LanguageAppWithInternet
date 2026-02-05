package com.example.language_app_with_internet.data.network.model

data class UserDto(
    val id: String,
    val name: String,
    val points: Int,
    val avatarUrl: String? = null // Игнорируем или оставляем, но не используем
)