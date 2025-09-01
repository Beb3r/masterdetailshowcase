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
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "MasterDetailShowCase"

include(":app")
include(":libraries")
include(":libraries:common")
include(":libraries:design")
include(":libraries:navigation")
include(":libraries:navigation:api-impl")
include(":libraries:navigation:api")
include(":libraries:network:api")
include(":libraries:network:api-impl")
include(":libraries:persistence:api")
include(":libraries:persistence:api-impl")
include(":libraries:translations")
include(":features:characters")
include(":features:characters:domain")
include(":features:characters:data")
include(":features:episodes")
include(":features:episodes:data")
include(":features:episodes:domain")
include(":features:episode-details")
include(":features:episode-details:navigation")
include(":features:episode-details:presentation")
include(":features:home")
include(":features:home:domain")
include(":features:home:navigation")
include(":features:home:presentation")
include(":features:character-details")
include(":features:character-details:navigation")
include(":features:character-details:presentation")
include(":features:session")
include(":features:session:data")
include(":features:session:domain")
