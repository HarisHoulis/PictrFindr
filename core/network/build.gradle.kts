plugins {
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    id("android.lib")
    id("android.hilt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin.test")
}

android {
    namespace = "com.houlis.haris.picfind.core.network"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt")
            )
        }
    }
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    with(libs) {
        implementation(projects.core.domain)
        implementation(projects.core.models)
        implementation(androidx.datastore.datastore.preferences)
        implementation(org.jetbrains.kotlinx.kotlinx.coroutines.android)
        implementation(org.jetbrains.kotlinx.kotlinx.serialization.json)
        implementation(com.squareup.okhttp3.logging.interceptor)
        implementation(com.squareup.retrofit2.retrofit)
        implementation(com.jakewharton.retrofit.retrofit2.kotlinx.serialization.converter)
        implementation(dev.forkhandles.result4k)

        testImplementation(projects.core.test)
    }
}
