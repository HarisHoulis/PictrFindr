plugins {
    id("android.lib")
    id("android.hilt")
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
}

android {
    namespace = "com.houlis.haris.core.test"

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
    implementation(projects.core.domain)
    implementation(projects.core.models)
    implementation(projects.core.network)

    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)
    implementation(libs.org.jetbrains.kotlinx.kotlinx.serialization.json)
    implementation(libs.dev.forkhandles.result4k)

}
