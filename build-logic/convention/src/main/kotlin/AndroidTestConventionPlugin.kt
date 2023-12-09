import com.houlis.haris.pictrfindr.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("kotlin.test")
            }

            dependencies {
                add("debugImplementation", libs.findLibrary("androidx.compose.bom").get())
                add("debugImplementation", libs.findLibrary("androidx.compose.ui.testManifest").get())

                add("testImplementation", platform(libs.findLibrary("junit5-bom").get()))
                add("testImplementation", platform(libs.findLibrary("androidx.compose.bom").get()))
                add("testImplementation", libs.findBundle("android-test").get())

                add("androidTestImplementation", kotlin("test"))
                add("androidTestImplementation", platform(libs.findLibrary("junit5-bom").get()))
                add("testRuntimeOnly", libs.findLibrary("junit-jupiter-vintage").get())
                add("androidTestImplementation", libs.findBundle("android-test").get())
            }
        }
    }
}
