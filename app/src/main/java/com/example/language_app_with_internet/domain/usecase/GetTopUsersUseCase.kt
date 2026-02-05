package com.example.language_app_with_internet.domain.usecase

import com.example.language_app_with_internet.common.Constants
import com.example.language_app_with_internet.domain.models.TopUser
import com.example.language_app_with_internet.domain.repository.TopUsersRepository

class GetTopUsersUseCase(
    private val repository: TopUsersRepository
) {
    suspend operator fun invoke(): List<TopUser> {
        val allUsers = repository.getTopUsers().sortedByDescending { it.points }
        val myId = Constants.MY_USER_ID

        if (myId.isEmpty()) return emptyList()

        val myIndex = allUsers.indexOfFirst { it.id == myId }
        
        // Если меня нет в списке или список пуст - показываем топ 4
        if (myIndex == -1) {
            return allUsers.take(4).mapIndexed { index, user ->
                user.copy(rank = index + 1, isMe = false)
            }
        }

        // Логика: показать 4 пользователя, так чтобы я был на 3-й позиции (индекс 2 в выборке), 
        // если это возможно (как на скрине: 5, 6, 7-Я, 8).
        // Значит начало выборки = мой индекс - 2.
        var startIndex = myIndex - 2
        
        // Корректируем границы
        if (startIndex < 0) startIndex = 0
        
        // Берем 4 элемента
        var endIndex = startIndex + 4
        if (endIndex > allUsers.size) {
            endIndex = allUsers.size
            // Если уперлись в конец, сдвигаем начало назад, чтобы набрать 4 (если есть)
            startIndex = (endIndex - 4).coerceAtLeast(0)
        }

        return allUsers.subList(startIndex, endIndex).mapIndexed { index, user ->
            user.copy(
                rank = startIndex + index + 1,
                isMe = user.id == myId
            )
        }
    }
}