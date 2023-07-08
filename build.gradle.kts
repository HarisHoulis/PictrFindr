buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.android.secrets) apply false
}

tasks.register<Copy>("installGitHooks") {
    from(layout.projectDirectory.dir("scripts/hooks"))
    into(File(rootDir, "/.git/hooks"))
    fileMode = 775
}
