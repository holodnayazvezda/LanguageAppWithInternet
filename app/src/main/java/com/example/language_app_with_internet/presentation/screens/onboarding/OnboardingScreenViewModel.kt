package com.example.language_app_with_internet.presentation.screens.onboarding

import androidx.lifecycle.ViewModel
import com.example.languageapp.data.storage.AppStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingScreenViewModel(
    private val appStorage: AppStorage
): ViewModel() {
    private var _onbScreenNumber = MutableStateFlow(0)
    val onbScreenNumber = _onbScreenNumber.asStateFlow()


    fun next() {
        appStorage.onboardingScreenNumber = onbScreenNumber.value + 1
    }

    fun skip() {
        appStorage.onboardingScreenNumber = 3
    }

    init {
        _onbScreenNumber.value = appStorage.onboardingScreenNumber
    }
}