import com.android.build.api.dsl.LibraryExtension
import com.houlis.haris.pictrfindr.configureAndroidCompose
import com.houlis.haris.pictrfindr.configureKotlinAndroid
import com.houlis.haris.pictrfindr.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.project

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("android.lib")
                apply("android.hilt")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
                configureKotlinAndroid(this)
                packaging {
                    resources {
                        excludes.add("META-INF/LICENSE.md")
                        excludes.add("META-INF/LICENSE-notice.md")
                    }
                }
            }

            dependencies {
                "implementation"(project(":core:network"))
                "implementation"(project(":core:design"))
                "implementation"(libs.findLibrary("androidx.hilt.navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                "implementation"(libs.findLibrary("coil.kt").get())
                "implementation"(libs.findLibrary("coil.kt.compose").get())
                "implementation"(libs.findLibrary("kotlinx.coroutines.android").get())

                "testImplementation"(project(":core:test"))
                "testImplementation"(kotlin("test"))

                "androidTestImplementation"(project(":core:test"))
                "androidTestImplementation"(kotlin("test"))
            }
        }
    }
}
