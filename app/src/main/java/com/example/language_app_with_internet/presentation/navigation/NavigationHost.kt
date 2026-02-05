package com.example.language_app_with_internet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.language_app_with_internet.presentation.screens.main.MainScreen
import com.example.language_app_with_internet.presentation.screens.onboarding.OnboardingScreen
import com.example.language_app_with_internet.presentation.screens.select_language.SelectLanguageScreen
import com.example.language_app_with_internet.presentation.screens.splash.SplashScreen
import com.example.language_app_with_internet.presentation.screens.splash.SplashScreenViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
) {
    val splashScreenViewModel: SplashScreenViewModel = koinViewModel(
        parameters = {
            parametersOf(
                { navController.navigate(NavRoutes.MAIN.route) },
            )
        }
    )
    NavHost(navController = navController, startDestination = NavRoutes.SPLASH.route) {
        composable(NavRoutes.SPLASH.route) {
            SplashScreen(
                viewModel = splashScreenViewModel
            )
        }
        composable(NavRoutes.ONBOARDING.route) {
            OnboardingScreen(
                navigateToLanguageSelect = { navController.navigate(NavRoutes.CHOOSE_LANGUAGE.route) }
            )
        }
        composable(NavRoutes.CHOOSE_LANGUAGE.route) {
            SelectLanguageScreen()
        }
        composable(NavRoutes.MAIN.route) {
            MainScreen()
        }
    }
}