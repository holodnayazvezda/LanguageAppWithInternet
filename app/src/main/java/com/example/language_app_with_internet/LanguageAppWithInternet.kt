package com.example.language_app_with_internet

import android.app.Application
import com.example.language_app_with_internet.data.di.dataModule
import com.example.language_app_with_internet.domain.di.domainModule
import com.example.language_app_with_internet.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LanguageAppWithInternet : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LanguageAppWithInternet)
            modules(
                presentationModule,
                domainModule,
                dataModule
            )
        }
    }
}