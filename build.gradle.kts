buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

}

plugins {
    // False-positive error for `libs`: https://github.com/gradle/gradle/issues/22797
    // Suppression doesn't work here :(
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
}

tasks.register<Copy>("installGitHooks") {
    from(layout.projectDirectory.dir("scripts/hooks"))
    into(File(rootDir, "/.git/hooks"))
    fileMode = 775
}
