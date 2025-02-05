@file:Suppress("UnstableApiUsage")

include(":features:episode-details:presentation")


include(":features:episode-details:navigation")


include(":features:episode-details")


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
include(":core:common")
include(":core:characters")
include(":core:characters:domain")
include(":core:characters:data")
include(":core:design")
include(":core:episodes")
include(":core:episodes:data")
include(":core:episodes:domain")
include(":core:navigation")
include(":core:navigation:api-impl")
include(":core:navigation:api")
include(":core:network:api")
include(":core:network:api-impl")
include(":core:persistence:api")
include(":core:persistence:api-impl")
include(":core:session")
include(":core:session:data")
include(":core:session:domain")
include(":core:translations")
include(":features:home")
include(":features:home:domain")
include(":features:home:navigation")
include(":features:home:presentation")
include(":features:character-details")
include(":features:character-details:navigation")
include(":features:character-details:presentation")
