pluginManagement {
    repositories {
        maven("https://maven.myket.ir")
        maven("https://jitpack.io")
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
        maven("https://jitpack.io")
        maven("https://maven.myket.ir")
        google()
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "Local_api"
include(":app")
include(":data")
include(":domain")
include(":presentation")
include(":core")
