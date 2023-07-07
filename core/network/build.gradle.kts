@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.serialization)
    id("android.lib")
    id("android.hilt")
}

android {
    namespace = "com.houlis.haris.core.network"

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
    with(libs) {
        implementation(project(":core:domain"))
        implementation(project(":core:models"))
        implementation(androidx.dataStore.preferences)
        implementation(kotlinx.coroutines.android)
        implementation(kotlinx.serialization.json)
        implementation(okhttp.logging)
        implementation(retrofit.core)
        implementation(retrofit.kotlin.serialization)
        implementation(libs.result4k)

        testImplementation(project(":core:test"))
    }
}
