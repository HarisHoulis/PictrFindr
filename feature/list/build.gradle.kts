plugins {
    id("android.feature")
    id("android.test")
}

android {
    namespace = "com.houlis.haris.feature.list"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.result4k)
}

