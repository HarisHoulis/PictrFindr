plugins {
    id("android.lib")
}

android {
    namespace = "com.houlis.haris.pictrfindr.ui.common.testutil"
}

dependencies {
    implementation(platform(libs.junit5.bom))

    implementation(project(":ui:common:mvi"))
    implementation(project(":core:coroutines"))

    implementation(libs.bundles.test)
}
