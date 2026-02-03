package com.example.language_app_with_internet

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LanguageAppWithInternet : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LanguageAppWithInternet)
            modules(

            )
        }
    }
}