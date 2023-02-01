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
