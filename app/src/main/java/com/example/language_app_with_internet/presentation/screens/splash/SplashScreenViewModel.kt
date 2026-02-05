package com.example.language_app_with_internet.presentation.screens.splash

import androidx.lifecycle.ViewModel
import com.example.languageapp.data.storage.AppStorage

class SplashScreenViewModel(
    private val appStorage: AppStorage,
    val navigateToMain: () -> Unit
): ViewModel() {
    fun navigateNext() {
        navigateToMain()
    }
}