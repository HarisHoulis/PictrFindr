plugins {
    id("android.app")
    id("android.compose.app")
    id("android.hilt")
}

android {
    namespace = "com.houlis.haris.picfind"

    defaultConfig {
        applicationId = "com.houlis.haris.picfind"
        minSdk = 22
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
}

dependencies {
    implementation(projects.ui.feature.list)
    implementation(projects.ui.feature.details)
}

afterEvaluate {
    tasks
        .filter { task ->
            task.name.equals("clean", ignoreCase = true) ||
                    task.name.contains("assemble", ignoreCase = true)
        }.forEach { task ->
            task.dependsOn(":installGitHooks")
        }
}
