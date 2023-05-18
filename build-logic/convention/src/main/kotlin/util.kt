@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import gradle.kotlin.dsl.accessors._6e086f953609a9f6cf2e2a0981eb6f58.kotlinOptions
import gradle.kotlin.dsl.accessors._f5177550c20f82cf6909e67a1981b117.kotlinOptions
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate

/**
 * Workaround until [VersionCatalog]s are accessible from precompiled script plugins.
 * See [src](https://github.com/gradle/gradle/issues/15383)
 */
internal val Project.libs: VersionCatalog
    get() = (this as ExtensionAware)
        .extensions
        .getByType<VersionCatalogsExtension>()
        .named("libs")

/**
 * Convenience fun to avoid calling [VersionCatalog.findLibrary.get]
 */
internal fun VersionCatalog.getLib(alias: String) = findLibrary(alias).get()

/**
 * Convenience method to configure Compose properties for [LibraryExtension]s
 */
fun LibraryExtension.configureCompose(project: Project) {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = project.libs.findVersion("androidxComposeCompiler").get().toString()
    }
}

/**
 * Convenience method to configure Compose properties for [BaseAppModuleExtension]s
 */
fun BaseAppModuleExtension.configureCompose(project: Project) {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = project.libs.findVersion("androidxComposeCompiler").get().toString()
    }
}

fun LibraryExtension.configureKotlin(project: Project) {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        val warningsAsErrors: String? by project
        allWarningsAsErrors = warningsAsErrors.toBoolean()
        jvmTarget = JavaVersion.VERSION_17.toString()
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
        )
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }

    project.dependencies {
        add("coreLibraryDesugaring", project.libs.getLib("android.desugarJdkLibs"))
    }
}

fun BaseAppModuleExtension.configureKotlin(project: Project) {
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        val warningsAsErrors: String? by project
        allWarningsAsErrors = warningsAsErrors.toBoolean()
        jvmTarget = JavaVersion.VERSION_17.toString()
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
        )
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }

    project.dependencies {
        add("coreLibraryDesugaring", project.libs.getLib("android.desugarJdkLibs"))
    }
}
