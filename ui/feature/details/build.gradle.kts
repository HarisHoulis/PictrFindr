plugins {
    id("android.feature")
    id("android.test")
}

android {
    namespace = "com.houlis.haris.picfind.feature.details"
}

dependencies {
    implementation(projects.core.coroutines)
    implementation(projects.core.domain)
    implementation(projects.ui.common.mvi)

    testImplementation(projects.ui.common.testutil)
}
