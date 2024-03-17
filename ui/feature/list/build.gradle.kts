plugins {
    id("android.feature")
    id("android.test")
}

android {
    namespace = "com.houlis.haris.picfind.feature.list"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(projects.core.coroutines)
    implementation(projects.core.domain)
    implementation(projects.ui.common.mvi)

    implementation(libs.dev.forkhandles.result4k)

    testImplementation(projects.ui.common.testutil)
}
