package dev.muskelmekka.cannons.history

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import dev.muskelmekka.cannons.auth.AuthRepository
import dev.muskelmekka.cannons.history.models.CompletedExercise
import dev.muskelmekka.cannons.history.models.CompletedSet
import dev.muskelmekka.cannons.history.models.Kg
import dev.muskelmekka.cannons.history.models.RecentWorkout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.datetime.toKotlinInstant
import javax.inject.Inject

class HistoryRepository @Inject constructor(
  private val firestore: FirebaseFirestore,
  private val authRepository: AuthRepository,
) {
  suspend fun getRecentWorkouts(): List<RecentWorkout> = withContext(Dispatchers.IO) {
    val uid = authRepository.requireCurrentUser().uid
    val snapshot = firestore.collection("users/$uid/history").get().await()

    val workouts = snapshot.documents.map { async { it.toRecentWorkout() } }

    workouts.awaitAll()
  }
}

private suspend fun DocumentSnapshot.toRecentWorkout() = coroutineScope {
  val exercisesList = get("exercises") as List<Map<String, Any>>? ?: emptyList()

  val exercises = exercisesList.map {
    async {
      val documentReference = it.getValue("exerciseRef") as DocumentReference
      val sets = it.getValue("sets") as List<Map<String, Double>>

      documentReference.get().await().toCompletedExercise(sets)
    }
  }

  RecentWorkout(
    id = id,
    name = requireNotNull(getString("name")),
    startedAt = requireNotNull(getTimestamp("startedAt")).toInstant(),
    endedAt = requireNotNull(getTimestamp("endedAt")).toInstant(),
    exercises = exercises.awaitAll(),
  )
}

private suspend fun DocumentSnapshot.toCompletedExercise(rawSets: List<Map<String, Double>>) = coroutineScope {
  val sets = rawSets.map { it.toCompletedSet() }

  CompletedExercise(
    name = requireNotNull(getString("name")),
    equipment = requireNotNull(getString("equipment")),
    muscle = requireNotNull(getString("muscle")),
    sets = sets,
  )
}

private fun Map<String, Double>.toCompletedSet(): CompletedSet {
  val weight = getValue("weight").kg
  val repCount = getValue("repCount").toInt()

  return CompletedSet(
    weight = weight,
    repCount = repCount,
  )
}

private fun Timestamp.toInstant() = toDate().toInstant().toKotlinInstant()
private val Double.kg: Kg get() = Kg(this.toFloat())
