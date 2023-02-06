plugins {
    id("android-feature")
}

android {
    namespace = "com.houlis.haris.feature.details"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.result4k)
}
