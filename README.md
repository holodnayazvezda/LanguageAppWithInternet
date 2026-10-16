# LanguageAppWithInternet

Учебное Android-приложение для языкового сервиса с сетевой загрузкой рейтинга пользователей. Включает вводные экраны, выбор языка, регистрацию и главный экран. Используются Kotlin, Jetpack Compose, Koin, Retrofit и Coil.

## Сборка и запуск

Откройте корень проекта в Android Studio, установите Android SDK 36 и выполните синхронизацию Gradle. Используйте JDK, совместимый с версией Android Gradle Plugin проекта; настройки Java указаны в `app/build.gradle.kts`. Минимальная версия устройства — Android API 27.

Для сборки отладочного APK:

```sh
sh gradlew :app:assembleDebug
```

В Windows: `gradlew.bat :app:assembleDebug`. APK появится в `app/build/outputs/apk/debug/`. Для запуска выберите модуль `app` и устройство или эмулятор в Android Studio.

## Устройство проекта

Базовый URL задаётся в `app/src/main/java/com/example/language_app_with_internet/data/di/DataModule.kt`. Значение `http://10.0.2.2:3000/` рассчитано на сервер на компьютере с Android Emulator. Контракт запросов — `data/network/ApiService.kt`, идентификатор пользователя — `common/Constants.kt`.
