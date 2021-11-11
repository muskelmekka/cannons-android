package dev.muskelmekka.cannons.programs.models

data class Workout(
  val id: String,
  val name: String,
  val mainMuscle: String,
  val otherMuscles: List<String>,
  val exercises: List<Exercise>,
)
