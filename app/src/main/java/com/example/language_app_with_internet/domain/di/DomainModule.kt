package com.example.language_app_with_internet.domain.di

import com.example.language_app_with_internet.domain.usecase.GetTopUsersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetTopUsersUseCase(repository = get()) }
}