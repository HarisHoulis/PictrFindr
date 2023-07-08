plugins {
    id("android.lib")
    id("android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.houlis.haris.core.domain"

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
    implementation(libs.result4k)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(project(":core:test"))
}
