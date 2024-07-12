# Dependencies and external libraries used

Jetpack Compose: A library for creating dynamic user interfaces on Android.
Room: A library for working with SQLite databases at the object-relational mapping (ORM) abstraction level.
Hilt is a Dependency Injection library from Google
AndroidX Lifecycle: A library for managing the lifecycle of Android components.
Kotlin Coroutines: For asynchronous programming.
Kotlin Standard Library: Basic features and types of the Kotlin language.
Desugaring is a process that allows you to use new features of the Java and Kotlin language

# Troubleshooting tips and common problems

Accessibility Issues: Make sure your AccessibilityService is configured correctly and has all the necessary permissions.
Database issues: Closely monitor database versions and migrations to avoid existing schema conflicts when upgrading your application.
Frontend Performance: Use asynchronous operations (such as Kotlin Coroutines) to perform long-running operations in the background to avoid blocking the main thread.

# External APIs and libraries

Jetpack Compose: Used to create modern user interfaces using a declarative approach.
Room: Selected to work with SQLite database due to its Jetpack integration and user-friendly ORM approach.
AndroidX Lifecycle: Used to manage the lifecycle of application components such as Activity and Fragment.
Kotlin Coroutines: Introduced to provide asynchronous processing of queries and database operations without blocking the main thread.


# Overview of the code structure and important modules

data:
model - Data classes (for example, Query for a query model).
database - Room database configuration, DAO and converter types.

di: This module can contain the configuration and provisioning of dependencies such as provideDatabase, repositories, services and other objects that are required to inject dependencies into various parts of the application.

services:
This module contains services such as AccessibilityService, background services, or other services that perform long-running operations or process data in the background.

ui:
screens - Application screens (for example, MainActivity and QueryListScreen).
QueryListScreen: Screen displaying a list of queries or other data

components - Jetpack Compose UI components (such as QueryList and QueryItem).

utils:
Helper utilities and helper classes that can be used in different parts of an application to perform common tasks.

viewmodels - ViewModel for managing data and business logic of screens.

# Залежності та зовнішні бібліотеки.

Jetpack Compose: Бібліотека для створення динамічних інтерфейсів на Android.
Room: Бібліотека для роботи з базами даних SQLite на рівні абстракції об'єктно-реляційного відображення (ORM).
Hilt – це бібліотека для впровадження залежностей (Dependency Injection) від Google
AndroidX Lifecycle: Бібліотека для керування життєвим циклом компонентів Android.
Kotlin Coroutines: Для асинхронного програмування.
Kotlin Standard Library: Основні функції та типи мови Kotlin.
Desugaring - це процес, який дозволяє використовувати нові можливості мови Java та Kotlin

# Поради щодо усунення несправностей та поширені проблеми

Проблеми з доступністю: Переконайтеся, що AccessibilityService налаштований правильно і має всі необхідні дозволи.
Проблеми з базою даних: Уважно стежте за версіями та міграціями бази даних, щоб уникнути конфліктів існуючих схем під час оновлення програми.
Продуктивність інтерфейсу: Використовуйте асинхронні операції (наприклад, Kotlin Coroutines) для тривалих операцій у фоновому режимі, щоб не блокувати головний потік.

# Зовнішні API та бібліотеки
Jetpack Compose: Використовується для створення сучасних інтерфейсів користувача з використанням декларативного підходу.
Room: Вибраний для роботи з базою даних SQLite через свою інтеграцію з Jetpack та зручний ORM підхід.
AndroidX Lifecycle: Використовується для керування життєвим циклом компонентів програми, таких як Activity та Fragment.
Kotlin Coroutines: Введені для забезпечення асинхронної обробки запитів та операцій із базою даних без блокування головного потоку.


# Огляд структури коду та важливих модулів


data:
model – Класи даних (наприклад, Query для моделі запитів).
database - Конфігурація бази даних Room, DAO та типи конвертерів.

di: У цьому модулі може бути налаштовування та надання залежностей, таких як програма Database, репозиторії, сервіси та інші об'єкти, які потрібні для впровадження залежностей у різні частини додатка.

services:
Цей модуль містить сервіси, такі як служби доступності (AccessibilityService), фонові служби або інші служби, які виконують тривалі операції або службовці для обробки даних у фоновому режимі.

ui:
screens - Екрани програми (наприклад, MainActivity і QueryListScreen).
QueryListScreen: Екран, який відображає список запитів або інших даних

components - Компоненти інтерфейсу користувача Jetpack Compose(наприклад QueryItem і ClickableTextComponent)

utils:
Допоміжні утиліти та допоміжні класи, які можуть використовуватись у різних частинах програми для виконання спільних завдань.
(Constants і DateTypeConverter)

viewmodels - ViewModel для управління даними та бізнес-логікою екранів.