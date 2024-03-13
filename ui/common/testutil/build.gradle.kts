plugins {
    id("android.lib")
}

android {
    namespace = "com.houlis.haris.pictrfindr.ui.common.testutil"
}

dependencies {
    implementation(platform(libs.org.junit.junit.bom))

    implementation(project(":ui:common:mvi"))
    implementation(project(":core:coroutines"))

    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.ktx)
    implementation(libs.bundles.test)
}
