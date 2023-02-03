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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        val warningsAsErrors: String? by project
        allWarningsAsErrors = warningsAsErrors.toBoolean()
        jvmTarget = JavaVersion.VERSION_11.toString()
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
        )
    }

    configureCompose(project)

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}


dependencies {
    add("debugImplementation", libs.getLib("androidx.compose.ui.tooling"))
    add("debugImplementation", libs.getLib("androidx.compose.ui.tooling.preview"))

    val bom = libs.getLib("androidx-compose-bom")
    add("implementation", platform(bom))
    add("implementation", libs.getLib("androidx.activity.compose"))
    add("implementation", libs.getLib("androidx.compose.material3"))
    add("implementation", libs.getLib("androidx.compose.ui"))
    add("implementation", libs.getLib("androidx.compose.ui.tooling.preview"))
    add("implementation", libs.getLib("androidx.hilt.navigation.compose"))
    add("implementation", libs.getLib("androidx.lifecycle.runtimeCompose"))
    add("implementation", libs.getLib("androidx.lifecycle.viewModelCompose"))
    add("implementation", libs.getLib("androidx.navigation.compose"))

    //TODO check if we need that: implementation "androidx.compose.ui:ui.graphics"

    add("androidTestImplementation", platform(bom))
}
