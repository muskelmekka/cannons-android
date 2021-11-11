package dev.muskelmekka.cannons.programs.models

data class Program(
  val id: String,
  val name: String,
  val workouts: List<Workout>,
)
