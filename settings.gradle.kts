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
include(":core:common")
include(":core:characters")
include(":core:characters:domain")
include(":core:characters:data")
include(":features:home")
include(":features:home:presentation")
include(":features:character-details")
include(":features:character-details:presentation")
include(":core:navigation")
include(":core:network:api")
include(":core:network:api-impl")
include(":core:persistence:api")
include(":core:persistence:api-impl")
include(":core:navigation:api-impl")
include(":core:navigation:api")
