package com.example.language_app_with_internet.presentation.di

import com.example.language_app_with_internet.presentation.screens.onboarding.OnboardingScreenViewModel
import com.example.language_app_with_internet.presentation.screens.splash.SplashScreenViewModel
import org.koin.dsl.module

val presentationModule = module {
    single {
        OnboardingScreenViewModel(
            appStorage = get()
        )
    }

    single { params ->
        SplashScreenViewModel(
            appStorage = get(),
            navigateToMain = params.get(),
        )
    }
}