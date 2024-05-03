@file:Suppress("UnstableApiUsage")



pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://www.jitpack.io")
    }
}
rootProject.name = "MovieNight"
include(":app")
include(":core")
