package com.example.language_app_with_internet.domain.models

data class TopUser(
    val id: String = "",
    val rank: Int,
    val name: String,
    val points: Int,
    val isMe: Boolean
)