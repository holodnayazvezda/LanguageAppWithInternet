package com.example.language_app_with_internet.data.repository

import com.example.language_app_with_internet.data.network.ApiService
import com.example.language_app_with_internet.domain.models.TopUser
import com.example.language_app_with_internet.domain.repository.TopUsersRepository

class TopUsersRepositoryImpl(
    private val apiService: ApiService
) : TopUsersRepository {
    override suspend fun getTopUsers(): List<TopUser> {
        // Загружаем DTO и мапим в Domain модель.
        // Rank (позиция) пока 0, посчитаем в UseCase после сортировки.
        // IsMe тоже false, посчитаем в UseCase.
        return apiService.getTopUsers().map { dto ->
            TopUser(
                rank = 0,
                name = dto.name,
                points = dto.points,
                isMe = false, 
                id = dto.id // Добавим id в TopUser, чтобы найти себя (надо обновить модель)
            )
        }
    }
}