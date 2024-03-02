plugins {
    alias(libs.plugins.kotlin.serialization)
    id("android.lib")
    id("android.hilt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin.test")
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

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    with(libs) {
        implementation(project(":core:domain"))
        implementation(project(":core:models"))
        implementation(androidx.dataStore.preferences)
        implementation(org.jetbrains.kotlinx.kotlinx.coroutines.android)
        implementation(kotlinx.serialization.json)
        implementation(okhttp.logging)
        implementation(retrofit.core)
        implementation(retrofit.kotlin.serialization)
        implementation(libs.result4k)

        testImplementation(project(":core:test"))
    }
}
