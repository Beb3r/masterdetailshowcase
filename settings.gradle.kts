@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "MasterDetailShowCase"
include(":app")
include(":core")
include(":features")
include(":core:movies")
include(":core:movies:domain")
include(":core:movies:data")
include(":features:home")
include(":features:home:presentation")
include(":features:movie-details")
include(":features:movie-details:presentation")
include(":core:persistence")
include(":core:network")
include(":core:navigation")
include(":core:network:api")
include(":core:network:api-impl")
