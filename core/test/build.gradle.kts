plugins {
    id("android-lib")
    id("android-hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.houlis.haris.core.test"
}

dependencies {
    implementation(project(":core:network"))
    implementation(libs.kotlinx.serialization.json)

    api(libs.junit4)
    api(libs.junit5.bom)
    api(libs.junit.jupiter)
    api(libs.androidx.test.core)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
    api(libs.hilt.android.testing)
    api(libs.kotest)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)
}
