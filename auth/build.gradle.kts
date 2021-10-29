plugins {
  id("com.android.library")

  kotlin("android")
  kotlin("kapt")

  id("de.mannodermaus.android-junit5")
  id("dagger.hilt.android.plugin")
}

android {
  compileSdk = BuildValues.compileSdkVersion

  defaultConfig {
    minSdk = BuildValues.minSdkVersion
    targetSdk = BuildValues.targetSdkVersion

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    resourceConfigurations.add("en")
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.androidx.compose.get()
  }

  kotlinOptions {
    jvmTarget = "1.8"

    freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
  }

  lint {
    htmlOutput = File("$buildDir/reports/lint/lint-results.html")
    xmlOutput = File("$buildDir/reports/lint/lint-results.xml")

    disable("DialogFragmentCallbacksDetector", "ObsoleteLintCustomCheck")
    warning("ConvertToWebp")
  }

  packagingOptions {
    resources.excludes.add("META-INF/AL2.0")
    resources.excludes.add("META-INF/LGPL2.1")
  }
}

dependencies {
  api(projects.auth.authModels)
  implementation(projects.dna)

  // Dagger
  kapt(libs.dagger.compiler)
  implementation(libs.dagger.dagger)

  // Dagger – Hilt
  implementation(libs.dagger.hilt.android)
  kapt(libs.dagger.hilt.compiler)

  // Firebase UI
  implementation(libs.firebase.ui.auth)

  // Kotlin
  implementation(libs.kotlin.stdLib)
}
