@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    id("android-lib")
    id("android-hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.houlis.haris.core.data"
}

dependencies {
    with(libs) {
        implementation(androidx.dataStore.preferences)
        implementation(kotlinx.coroutines.android)
        implementation(kotlinx.serialization.json)
        implementation(okhttp.logging)
        implementation(retrofit.core)
        implementation(retrofit.kotlin.serialization)
        implementation(result4k)

        testImplementation(project(":core:test"))
    }
}
