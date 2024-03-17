plugins {
    id("android.lib")
}

android {
    namespace = "com.houlis.haris.picfind.ui.common.testutil"
}

dependencies {
    implementation(platform(libs.org.junit.junit.bom))

    implementation(projects.ui.common.mvi)
    implementation(projects.core.coroutines)

    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.ktx)
    implementation(libs.bundles.test)
}
