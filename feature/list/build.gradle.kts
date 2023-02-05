plugins {
    id("android-feature")
}

android {
    namespace = "com.houlis.haris.feature.list"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.result4k)
}
