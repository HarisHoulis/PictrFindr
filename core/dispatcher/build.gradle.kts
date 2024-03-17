plugins {
    id("android.lib")
    id("android.hilt")
}

android {
    namespace = "com.houlis.haris.picfind.core.dispatcher"
}

dependencies {
    implementation(libs.bundles.coroutines)
}
