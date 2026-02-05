package com.example.language_app_with_internet.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.language_app_with_internet.presentation.theme.deepBlue

@Composable
fun BaseScreenBackground(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(deepBlue)
    ) {
        Spacer(Modifier.weight(0.2f))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
                .clip(RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp
                ))
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 25.dp)
        ) {
            Spacer(Modifier.height(20.dp))
            content()
        }

    }
}