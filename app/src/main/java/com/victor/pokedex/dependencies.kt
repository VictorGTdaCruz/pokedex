package com.victor.pokedex

object Versions {
    const val kotlin = "1.5.21"
    const val kotlinCoroutines = "1.4.1"
    const val androidGradleSupport = "7.0.4"

    const val compose = "1.0.1"
    const val androidxAppCompat = "1.4.0"
    const val androidxLifecycle = "2.4.0"
    const val androidxCore = "1.7.0"

    const val okhttp3 = "4.6.0"
    const val retrofit2 = "2.8.1"
    const val ktxConverter = "0.8.0"
    const val ktxSerialization = "1.0.1"

    const val kodein = "7.2.0"

    const val jUnit = "4.+"
}

object Dependencies {
    val kodein = "org.kodein.di:kodein-di:${Versions.kodein}"
    val compose = "androidx.compose.ui:ui:${Versions.compose}"
    val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
    val androidxActivityCompose = "androidx.activity:activity-compose::${Versions.androidxAppCompat}"
    val androidxLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycle}"
    val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    val materialDesign = "com.google.android.material:material:${Versions.androidxAppCompat}"

    val jUnit = "junit:junit:${Versions.jUnit}"
    val composeJUnit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
}

object BuildPlugins {
    val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradleSupport}"
    val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}