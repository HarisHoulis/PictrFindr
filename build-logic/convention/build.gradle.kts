import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.houlis.haris.pictrfindr.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.com.android.tools.build.gradle)
    compileOnly(libs.org.jetbrains.kotlin.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "android.app"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidComposeApplication") {
            id = "android.compose.app"
            implementationClass = "AndroidComposeApplicationConventionPlugin"
        }
        register("androidFeature") {
            id = "android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHIlt") {
            id = "android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidLibrary") {
            id = "android.lib"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidTest") {
            id = "android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
        register("kotlinTest") {
            id = "kotlin.test"
            implementationClass = "TestConventionPlugin"
        }
    }
}
