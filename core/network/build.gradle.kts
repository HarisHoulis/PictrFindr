plugins {
    id("android-lib")
    id("android-hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.houlis.haris.network"
}

dependencies {
    with(libs) {
        implementation(kotlinx.coroutines.android)
        implementation(kotlinx.serialization.json)
        implementation(okhttp.logging)
        implementation(retrofit.core)
        implementation(retrofit.kotlin.serialization)
        implementation(timber)
        implementation(libs.result4k)

        testImplementation(project(":core:test"))
    }
}
