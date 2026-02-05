package com.example.language_app_with_internet.data.di

import com.example.language_app_with_internet.data.network.ApiService
import com.example.language_app_with_internet.data.repository.TopUsersRepositoryImpl
import com.example.language_app_with_internet.domain.repository.TopUsersRepository
import com.example.languageapp.data.storage.AppStorage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    single { AppStorage(context = get()) }

    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }

    // Репозиторий
    single<TopUsersRepository> { TopUsersRepositoryImpl(apiService = get()) }
}