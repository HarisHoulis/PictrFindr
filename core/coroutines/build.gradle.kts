plugins {
    id("android.lib")
    id("android.hilt")
}

android {
    namespace = "com.houlis.haris.picfind.core.coroutines"
}

dependencies {
    implementation(projects.core.dispatcher)

    implementation(libs.bundles.coroutines)
}
