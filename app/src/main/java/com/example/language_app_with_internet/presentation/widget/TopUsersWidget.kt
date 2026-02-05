package com.example.language_app_with_internet.presentation.widget

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.language_app_with_internet.common.Constants
import com.example.language_app_with_internet.domain.models.TopUser
import com.example.language_app_with_internet.presentation.theme.deepBlue
import com.example.language_app_with_internet.presentation.theme.orange
import com.example.language_app_with_internet.presentation.theme.white
import com.example.language_app_with_internet.presentation.theme.dark
import com.example.languageapp.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TopUsersWidget : GlanceAppWidget() {

    override val stateDefinition = PreferencesGlanceStateDefinition

    @SuppressLint("RestrictedApi")
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val isLoggedIn = Constants.MY_USER_ID.isNotEmpty()
            
            // Читаем состояние
            val prefs = currentState<Preferences>()
            val json = prefs[TopUsersWidgetState.USERS_JSON_KEY]
            
            val users: List<TopUser> = if (json != null) {
                try {
                    val type = object : TypeToken<List<TopUser>>() {}.type
                    Gson().fromJson(json, type)
                } catch (e: Exception) {
                    emptyList()
                }
            } else {
                emptyList()
            }

            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(ColorProvider(deepBlue))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isLoggedIn) {
                    // Передаем реальные данные (или пустой список пока грузится)
                    LeaderboardContent(users)
                } else {
                    NotLoggedInContent()
                }
            }
        }
    }

    @SuppressLint("RestrictedApi")
    @Composable
    private fun NotLoggedInContent() {
        Text(
            text = "You should log in application",
            style = TextStyle(
                color = ColorProvider(white),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }

    @SuppressLint("RestrictedApi")
    @Composable
    private fun LeaderboardContent(users: List<TopUser>) {
        if (users.isEmpty()) {
            Text(
                text = "Loading...",
                style = TextStyle(color = ColorProvider(white))
            )
            return
        }

        // Ищем себя для заголовка
        val myUser = users.find { it.isMe }
        
        Column(
            modifier = GlanceModifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            if (myUser != null) {
                Text(
                    text = "You place is ${myUser.rank}! Awesome!",
                    style = TextStyle(
                        color = ColorProvider(white),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = GlanceModifier.padding(bottom = 12.dp)
                )
            } else {
                // Заголовок если меня нет в топ 4 или вообще
                Text(
                    text = "Top Users",
                    style = TextStyle(
                        color = ColorProvider(white),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = GlanceModifier.padding(bottom = 12.dp)
                )
            }

            Column(
                modifier = GlanceModifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                users.forEach { user ->
                    UserItem(user)
                    Spacer(modifier = GlanceModifier.height(8.dp))
                }
            }
        }
    }

    @Composable
    fun UserItem(user: TopUser) {
        val rowBackground = if (user.isMe) ColorProvider(orange) else ColorProvider(Color(0xFFEEEEEE))
        val textColor = ColorProvider(dark) 

        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .background(rowBackground)
                .cornerRadius(24.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${user.rank}.",
                style = TextStyle(
                    color = textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = GlanceModifier.width(32.dp)
            )

            Image(
                provider = ImageProvider(R.drawable.sample_ava), // Хардкод аватарки
                contentDescription = null,
                modifier = GlanceModifier.size(32.dp).cornerRadius(16.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = GlanceModifier.width(12.dp))

            Text(
                text = user.name,
                style = TextStyle(
                    color = textColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = GlanceModifier.defaultWeight()
            )

            Text(
                text = "${user.points} points",
                style = TextStyle(
                    color = textColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}