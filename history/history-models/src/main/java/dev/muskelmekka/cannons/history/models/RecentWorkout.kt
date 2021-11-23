package dev.muskelmekka.cannons.history.models

import kotlinx.datetime.Instant
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

data class RecentWorkout(
  val id: String,
  val name: String,
  val startedAt: Instant,
  val endedAt: Instant,
  val exercises: List<CompletedExercise>,
) {
  @ExperimentalTime
  val duration: Duration
    get() = endedAt - startedAt
}
