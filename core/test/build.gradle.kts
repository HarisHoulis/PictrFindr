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
    implementation(project(":core:domain"))
    implementation(project(":core:models"))
    implementation(project(":core:network"))

    implementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.result4k)

}
