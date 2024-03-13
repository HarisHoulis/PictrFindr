plugins {
    id("android.app")
    id("android.compose.app")
    id("android.hilt")
}

android {
    namespace = "com.houlis.haris.pictrfindr"

    defaultConfig {
        applicationId = "com.houlis.haris.pictrfindr"
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
    implementation(project(":ui:feature:list"))
    implementation(project(":ui:feature:details"))
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
