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
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"

    freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
  }

  lint {
    htmlOutput = File("$buildDir/reports/lint/lint-results.html")
    xmlOutput = File("$buildDir/reports/lint/lint-results.xml")
  }

  packagingOptions {
    resources.excludes.add("META-INF/AL2.0")
    resources.excludes.add("META-INF/LGPL2.1")
  }
}

dependencies {
  // JUnit 5
  testImplementation(libs.junit5.jupiter.api)
  testRuntimeOnly(libs.junit5.jupiter.engine)

  // Kotlin
  implementation(libs.kotlin.stdLib)
}
