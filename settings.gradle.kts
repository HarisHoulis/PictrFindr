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

rootProject.name = "PictrFindr"
include(":app")
include(":core:coroutines")
include(":core:dispatcher")
include(":core:design")
include(":core:domain")
include(":core:models")
include(":core:network")
include(":core:test")
include(":feature:details")
include(":feature:list")
include(":ui:common:mvi")
include(":ui:common:testutil")
