package dev.muskelmekka.cannons.history.models

data class CompletedExercise(
  val name: String,
  val equipment: String,
  val muscle: String,
  val sets: List<CompletedSet>,
)
