plugins {
  id("com.android.library")

  kotlin("android")

  id("de.mannodermaus.android-junit5")
}

android {
  compileSdk = BuildValues.compileSdkVersion

  defaultConfig {
    minSdk = BuildValues.minSdkVersion
    targetSdk = BuildValues.targetSdkVersion

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    compose = true
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
  // AndroidX – Activity
  implementation(libs.androidx.activity.activity)
  implementation(libs.androidx.activity.compose)

  // AndroidX – Appcompat
  implementation(libs.androidx.appcompat)

  // AndroidX – Compose
  implementation(libs.androidx.compose.animation)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.material.material3)
  implementation(libs.androidx.compose.material.icons.core)
  implementation(libs.androidx.compose.material.icons.extended)
  implementation(libs.androidx.compose.runtime)
  implementation(libs.androidx.compose.ui.tooling)
  implementation(libs.androidx.compose.ui.ui)

  androidTestImplementation(libs.androidx.compose.ui.test)

  // AndroidX – Fragment
  implementation(libs.androidx.fragment)

  // AndroidX – Test
  androidTestImplementation(libs.androidx.test.core)
  androidTestImplementation(libs.androidx.test.rules)
  androidTestImplementation(libs.androidx.test.runner)

  // AndroidX – Test – Espresso
  androidTestImplementation(libs.androidx.test.espresso.core)

  // Accompanist
  implementation(libs.accompanist.insets)

  // JUnit 4
  androidTestImplementation(libs.junit4)

  // JUnit 5
  testImplementation(libs.junit5.jupiter.api)
  testRuntimeOnly(libs.junit5.jupiter.engine)

  // Kotlin
  implementation(libs.kotlin.stdLib)

  // Material Design Components
  implementation(libs.google.material)
}
