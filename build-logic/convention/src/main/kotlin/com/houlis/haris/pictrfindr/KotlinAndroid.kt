package com.houlis.haris.pictrfindr

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

private val PREFERRED_JAVA_VERSION = JavaVersion.VERSION_17

internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = 34

        defaultConfig {
            minSdk = 21
        }

        compileOptions {
            sourceCompatibility = PREFERRED_JAVA_VERSION
            targetCompatibility = PREFERRED_JAVA_VERSION
            isCoreLibraryDesugaringEnabled = true
        }

        testOptions {
            unitTests.isIncludeAndroidResources = true
            unitTests.all {
                it.useJUnitPlatform {
                    includeEngines("junit-jupiter", "junit-vintage")
                }
            }
        }

        dependencies {
            add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
            add("implementation", libs.findLibrary("org.jetbrains.kotlinx.kotlinx.collections.immutable").get())
        }

        configureKotlin()
    }
}

private fun Project.configureKotlin() {
    // Use withType to workaround https://youtrack.jetbrains.com/issue/KT-55947
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = PREFERRED_JAVA_VERSION.toString()
            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-Xcontext-receivers"
            )
        }
    }
}
