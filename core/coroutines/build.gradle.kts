plugins {
    id("android.lib")
    id("android.hilt")
}

android {
    namespace = "com.houlis.haris.pictrfindr.core.coroutines"
}

dependencies {
    implementation(project(":core:dispatcher"))

    implementation(libs.bundles.coroutines)
}
