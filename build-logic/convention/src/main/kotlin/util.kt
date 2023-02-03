@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.getByType

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
