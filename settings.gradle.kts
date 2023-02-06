pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

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
include(":core:design")
include(":core:domain")
include(":core:models")
include(":core:network")
include(":core:test")
include(":feature:details")
include(":feature:list")
