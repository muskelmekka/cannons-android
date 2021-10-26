plugins {
  id("com.diffplug.spotless") version "5.17.0"
}

allprojects {
  apply(plugin = "com.diffplug.spotless")

  spotless {
    val kotlinUserData = mapOf(
      "end_of_line" to "lf",
      "indent_size" to "2",
      "indent_style" to "space",
      "insert_final_newline" to "true",
      "max_line_length" to "120"
    )

    kotlin {
      target("**/*.kt")
      targetExclude("$buildDir/**/*.kt")

      ktlint("0.41.0").userData(kotlinUserData)
    }

    kotlinGradle {
      target("**/*.kts")

      ktlint("0.41.0").userData(kotlinUserData)
    }
  }
}
