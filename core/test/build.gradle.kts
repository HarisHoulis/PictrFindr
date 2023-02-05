@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
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

    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui.test)
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

    debugApi(libs.androidx.compose.ui.testManifest)
}
