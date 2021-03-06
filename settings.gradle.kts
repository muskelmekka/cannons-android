enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
  }

  plugins {
    id("de.mannodermaus.android-junit5") version "1.8.0.0"

    id("com.android.application") version "7.0.3" apply false
    id("com.android.library") version "7.0.3" apply false

    kotlin("android") version "1.5.31" apply false
    kotlin("kapt") version "1.5.31" apply false
  }

  resolutionStrategy {
    eachPlugin {
      when (requested.id.id) {
        "com.google.gms.google-services" -> useModule("com.google.gms:google-services:4.3.10")
        "dagger.hilt.android.plugin" -> useModule("com.google.dagger:hilt-android-gradle-plugin:2.39.1")
      }
    }
  }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "cannons-android"

include(":app")
include(":auth")
include(":auth:auth-models")
project(":auth:auth-models").projectDir = File("auth/auth-models")
include(":core-ui")
include(":dna")
include(":history")
include(":history:history-models")
project(":history:history-models").projectDir = File("history/history-models")
include(":home")
include(":profile")
include(":profile:profile-models")
project(":profile:profile-models").projectDir = File("profile/profile-models")
include(":programs")
include(":programs:programs-models")
project(":programs:programs-models").projectDir = File("programs/programs-models")
