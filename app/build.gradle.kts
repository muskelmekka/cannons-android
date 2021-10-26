plugins {
  id("com.android.application")

  kotlin("android")
  kotlin("kapt")

  id("de.mannodermaus.android-junit5")
  id("dagger.hilt.android.plugin")
}

android {
  compileSdk = BuildValues.compileSdkVersion

  defaultConfig {
    applicationId = "dev.muskelmekka.cannons"

    minSdk = BuildValues.minSdkVersion
    targetSdk = BuildValues.targetSdkVersion

    versionCode = 1
    versionName = "0.1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    compose = true
  }

  buildTypes {
    named("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
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
  implementation(projects.dna)

  // Accompanist
  implementation(libs.accompanist.insets)
  implementation(libs.accompanist.insetsUi)
  implementation(libs.accompanist.navigation)

  // AndroidX – Activity
  implementation(libs.androidx.activity.activity)
  implementation(libs.androidx.activity.compose)

  // AndroidX – Appcompat
  implementation(libs.androidx.appcompat)

  // AndroidX – Compose
  implementation(libs.androidx.compose.animation)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.material.material)
  implementation(libs.androidx.compose.material.icons.core)
  implementation(libs.androidx.compose.material.icons.extended)
  implementation(libs.androidx.compose.runtime)
  implementation(libs.androidx.compose.ui.tooling)
  implementation(libs.androidx.compose.ui.ui)

  androidTestImplementation(libs.androidx.compose.ui.test)

  // AndroidX – Fragment
  implementation(libs.androidx.fragment)

  // AndroidX – Hilt
  implementation(libs.androidx.hilt.viewModel)

  // AndroidX – Test
  androidTestImplementation(libs.androidx.test.core)
  androidTestImplementation(libs.androidx.test.rules)
  androidTestImplementation(libs.androidx.test.runner)

  // AndroidX – Test – Espresso
  androidTestImplementation(libs.androidx.test.espresso.core)

  // Dagger
  kapt(libs.dagger.compiler)
  implementation(libs.dagger.dagger)

  // Dagger – Hilt
  implementation(libs.dagger.hilt.android)
  kapt(libs.dagger.hilt.compiler)

  // JUnit 4
  androidTestImplementation(libs.junit4)

  // JUnit 5
  testImplementation(libs.junit5.jupiter.api)
  testRuntimeOnly(libs.junit5.jupiter.engine)

  // Kotlin
  implementation(libs.kotlin.stdLib)

  // Kotlinx – Coroutines
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.coroutines.core)
}
