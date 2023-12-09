plugins {
    id("android.feature")
    id("android.test")
}

android {
    namespace = "com.houlis.haris.feature.details"
}

dependencies {
    implementation(project(":core:domain"))
}
