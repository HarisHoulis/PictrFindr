plugins {
    id("android.lib")
    id("android.hilt")
    alias(libs.plugins.kotlin.serialization)
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
    implementation(project(":core:models"))
    implementation(project(":core:network"))
    implementation(project(":core:domain"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.result4k)

    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui.test)
    api(libs.junit4)
    api(libs.junit5.bom)
    api(libs.junit.jupiter)
    api(libs.androidx.test.core)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
    api(libs.hilt.android.testing)
    api(libs.kotest)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)

    debugApi(libs.androidx.compose.ui.testManifest)
}
