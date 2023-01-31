plugins {
    id("compose-app")
}

dependencies {

    debugImplementation (libs.androidx.compose.ui.tooling)
    debugImplementation (libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui)

    //TODO check if we need that: implementation "androidx.compose.ui:ui.graphics"

    testImplementation(libs.junit5.bom)
    testImplementation(libs.junit.jupiter)

    androidTestImplementation (libs.junit4)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso.core)
    androidTestImplementation (libs.androidx.compose.ui.test)

}
