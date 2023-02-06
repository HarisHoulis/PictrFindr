plugins {
    id("android-lib")
    id("android-hilt")
}

android {
    configureCompose(project)
    packagingOptions {
        resources {
            excludes.add("META-INF/LICENSE.md")
            excludes.add("META-INF/LICENSE-notice.md")
        }
    }
}

dependencies {
    add("implementation", project(":core:network"))
    add("implementation", project(":core:design"))
    add("implementation", libs.getLib("androidx.compose.bom"))
    add("implementation", libs.getLib("androidx.compose.foundation"))
    add("implementation", libs.getLib("androidx.compose.foundation.layout"))
    add("implementation", libs.getLib("androidx.compose.material3"))
    add("implementation", libs.getLib("androidx.compose.ui"))
    add("implementation", libs.getLib("androidx.hilt.navigation.compose"))
    add("implementation", libs.getLib("androidx.lifecycle.runtimeCompose"))
    add("implementation", libs.getLib("androidx.lifecycle.viewModelCompose"))
    add("implementation", libs.getLib("coil.kt"))
    add("implementation", libs.getLib("coil.kt.compose"))
    add("implementation", libs.getLib("kotlinx.coroutines.android"))

    add("testImplementation", project(":core:test"))
    add("testImplementation", kotlin("test"))

    add("androidTestImplementation", project(":core:test"))
    add("androidTestImplementation", kotlin("test"))
}
