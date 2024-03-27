plugins {
    id("android.feature")
    id("android.test")
}

android {
    namespace = "com.houlis.haris.picfind.feature.details"
}

dependencies {
    implementation(projects.core.coroutines)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.ui.common.mvi)
    implementation(projects.ui.common.navigation)

    testImplementation(projects.ui.common.testutil)
}
