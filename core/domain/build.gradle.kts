@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    id("android-lib")
    id("android-hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.houlis.haris.core.domain"
}

dependencies {
    implementation(project(":core:models"))
    implementation(libs.result4k)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(project(":core:test"))
}
