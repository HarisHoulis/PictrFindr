apply(plugin = "com.google.dagger.hilt.android")
plugins {
    kotlin("kapt")
}

dependencies {
    add("implementation", libs.findLibrary("hilt.android").get())
    add("kapt", libs.findLibrary("hilt.compiler").get())
    add("kaptAndroidTest", libs.findLibrary("hilt.compiler").get())
}

