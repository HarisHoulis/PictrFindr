plugins {
    id("android.lib")
    id("android.hilt")
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    id("kotlin.test")
}

android {
    namespace = "com.houlis.haris.picfind.core.domain"

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

dependencies {
    implementation(projects.core.models)
    implementation(libs.dev.forkhandles.result4k)
    implementation(libs.org.jetbrains.kotlinx.kotlinx.serialization.json)

    testImplementation(projects.core.test)
}
