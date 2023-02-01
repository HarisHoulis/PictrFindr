buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

}

  plugins {
      // False-positive error for `libs`: https://github.com/gradle/gradle/issues/22797
      alias(libs.plugins.android.application) apply false
      alias(libs.plugins.kotlin.jvm) apply false
      alias(libs.plugins.kotlin.serialization) apply false
      alias(libs.plugins.hilt) apply false
  }
