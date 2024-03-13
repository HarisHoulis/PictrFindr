plugins {
    id("android.feature")
    id("android.test")
}

android {
    namespace = "com.houlis.haris.pictrfindr.feature.list"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":core:coroutines"))
    implementation(project(":core:domain"))
    implementation(project(":ui:common:mvi"))

    implementation(libs.result4k)

    testImplementation(project(":ui:common:testutil"))
}
