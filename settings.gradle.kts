pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "PicFind"

include(":app")
include(":core:coroutines")
include(":core:dispatcher")
include(":core:design")
include(":core:domain")
include(":core:models")
include(":core:network")
include(":core:test")
include(":ui:feature:details")
include(":ui:feature:list")
include(":ui:common:mvi")
include(":ui:common:testutil")
