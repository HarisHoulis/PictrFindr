import com.android.build.api.dsl.LibraryExtension
import com.houlis.haris.picfind.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                compileSdk = 34
                packaging {
                    resources {
                        excludes.add("META-INF/LICENSE.md")
                        excludes.add("META-INF/LICENSE-notice.md")
                    }
                }
            }

            dependencies {
                "testImplementation"(kotlin("test"))
                "androidTestImplementation"(kotlin("test"))
            }
        }
    }
}
