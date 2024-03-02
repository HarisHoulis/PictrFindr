plugins {
    id("android.lib")
    id("android.test")
    id("kotlin.test")
}

android {
    namespace = "com.houlis.haris.pictrfindr.ui.common.mvi"
}

dependencies {
    implementation(project(":core:coroutines"))
    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.ktx)
    implementation(libs.com.jakewharton.timber.timber)
    implementation(libs.bundles.compose)
}
