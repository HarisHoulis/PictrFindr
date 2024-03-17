package com.houlis.haris.picfind

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion =
                project.libs.findVersion("androidx.kotlin.compose.compiler.version").get().toString()
        }

        testOptions {
            unitTests.all {
                it.useJUnitPlatform()
            }
        }

        dependencies {
            val bom = libs.findLibrary("androidx.compose.compose.bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findLibrary("androidx.activity.activity.compose").get())
            add("implementation", libs.findLibrary("androidx.compose.material3.material3").get())
            add("implementation", libs.findLibrary("androidx.compose.ui.ui").get())
            add("implementation", libs.findLibrary("androidx.navigation.navigation.compose").get())
        }
    }
}
