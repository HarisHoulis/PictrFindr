plugins {
    id("android.feature")
    id("android.test")
}

android {
    namespace = "com.houlis.haris.pictrfindr.feature.details"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":ui:common:mvi"))
}
