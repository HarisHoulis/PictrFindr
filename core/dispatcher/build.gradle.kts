plugins {
    id("android.lib")
    id("android.hilt")
}

android {
    namespace = "com.houlis.haris.pictrfindr.core.dispatcher"
}

dependencies {
    implementation(libs.bundles.coroutines)
}
