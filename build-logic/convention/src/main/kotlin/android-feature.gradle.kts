plugins {
    id("android-lib")
    id("android-hilt")
}

dependencies {
    add("implementation", project(":core:network"))
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
