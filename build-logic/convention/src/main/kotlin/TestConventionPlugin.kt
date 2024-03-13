import com.houlis.haris.pictrfindr.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class TestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findLibrary("androidx.compose.compose.bom").get())
                add("testImplementation", libs.findBundle("test").get())
            }
        }
    }
}
