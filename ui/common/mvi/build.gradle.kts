plugins {
    id("android.lib")
    id("android.test")
    id("kotlin.test")
}

android {
    namespace = "com.houlis.haris.picfind.ui.common.mvi"
}

dependencies {
    implementation(projects.core.coroutines)
    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.ktx)
    implementation(libs.com.jakewharton.timber.timber)
    implementation(libs.bundles.compose)
}
