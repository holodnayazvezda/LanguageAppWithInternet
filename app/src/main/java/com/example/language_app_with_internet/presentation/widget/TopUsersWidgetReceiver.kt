package com.example.language_app_with_internet.presentation.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.example.language_app_with_internet.domain.usecase.GetTopUsersUseCase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TopUsersWidgetReceiver : GlanceAppWidgetReceiver(), KoinComponent {
    override val glanceAppWidget: GlanceAppWidget = TopUsersWidget()
    
    // Инжектим UseCase
    private val getTopUsersUseCase: GetTopUsersUseCase by inject()
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onUpdate(
        context: Context,
        appWidgetManager: android.appwidget.AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        
        // Запускаем загрузку данных
        scope.launch {
            try {
                // 1. Получаем данные
                val users = getTopUsersUseCase() // Получаем уже отсортированный список (TopUser)
                val json = Gson().toJson(users)

                // 2. Сохраняем во все виджеты
                val glanceManager = GlanceAppWidgetManager(context)
                val glanceIds = glanceManager.getGlanceIds(TopUsersWidget::class.java)
                
                glanceIds.forEach { glanceId ->
                    updateAppWidgetState(context, PreferencesGlanceStateDefinition, glanceId) { prefs ->
                        prefs.toMutablePreferences().apply {
                            this[TopUsersWidgetState.USERS_JSON_KEY] = json
                        }
                    }
                    // 3. Обновляем UI
                    TopUsersWidget().update(context, glanceId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}