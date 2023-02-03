plugins {
    id("compose-app")
}

dependencies {
    implementation(project(":feature:list"))

    testImplementation(libs.junit5.bom)
    testImplementation(libs.junit.jupiter)

    androidTestImplementation (libs.junit4)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso.core)
    androidTestImplementation (libs.androidx.compose.ui.test)
}
