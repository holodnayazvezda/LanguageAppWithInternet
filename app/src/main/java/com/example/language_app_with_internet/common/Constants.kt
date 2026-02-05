package com.example.language_app_with_internet.common

import com.example.language_app_with_internet.domain.models.OnboardingModel
import com.example.languageapp.R
import java.util.Queue

object Constants {
    const val MY_USER_ID = "rerereref" // Hardcoded ID as requested

    val onboardingItems = listOf(
        OnboardingModel(
            image = R.drawable.onb1,
            title = "Confidence in your words",
            description = "With conversation-based learning,\nyou'll be talking from lesson one",
            buttonText = "Next"
        ),
        OnboardingModel(
            image = R.drawable.onb2,
            title = "Take your time to learn",
            description = "Develop a habit of learning and\nmake it a part of your daily routine",
            buttonText = "More"
        ),
        OnboardingModel(
            image = R.drawable.onb3,
            title = "The lessons you need to learn",
            description = "Using a variety of learning styles to learn\nand retain",
            buttonText = "Choose a language"
        ),
    )
}