plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("android-hilt")
}

android {
    namespace = "com.houlis.haris.pictrfindr"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.houlis.haris.pictrfindr"
        minSdk = 22
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }

    configureCompose(project)
    configureKotlin(project)
}


dependencies {
    val bom = libs.getLib("androidx-compose-bom")
    add("implementation", platform(bom))
    add("implementation", libs.getLib("androidx.activity.compose"))
    add("implementation", libs.getLib("androidx.compose.material3"))
    add("implementation", libs.getLib("androidx.compose.ui"))
    add("implementation", libs.getLib("androidx.navigation.compose"))
}
