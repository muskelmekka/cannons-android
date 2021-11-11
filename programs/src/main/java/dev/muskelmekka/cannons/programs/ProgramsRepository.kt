package dev.muskelmekka.cannons.programs

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import dev.muskelmekka.cannons.auth.AuthRepository
import dev.muskelmekka.cannons.programs.models.Exercise
import dev.muskelmekka.cannons.programs.models.Program
import dev.muskelmekka.cannons.programs.models.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProgramsRepository @Inject constructor(
  private val firestore: FirebaseFirestore,
  private val authRepository: AuthRepository,
) {
  suspend fun getPrograms(): List<Program> = withContext(Dispatchers.IO) {
    val uid = authRepository.requireCurrentUser().uid
    val snapshot = firestore.collection("users/$uid/programs").get().await()

    val programs = snapshot.documents.map { async { it.toProgram(it.id) } }

    programs.awaitAll()
  }
}

private suspend fun DocumentSnapshot.toProgram(id: String): Program = coroutineScope {
  val workoutsSnapshot = reference.collection("workouts").get().await()
  val workouts = workoutsSnapshot.documents.map { async { it.toWorkout(it.id) } }

  Program(
    id = id,
    name = requireNotNull(getString("name")),
    workouts = workouts.awaitAll(),
  )
}

private suspend fun DocumentSnapshot.toWorkout(id: String): Workout = coroutineScope {
  val exercisesList = get("exercises") as List<Map<String, DocumentReference>>? ?: emptyList()

  val exercises = exercisesList.map {
    async {
      val documentReference = it.getValue("exerciseRef")
      documentReference.get().await().toExercise(documentReference.id)
    }
  }

  Workout(
    id = id,
    name = requireNotNull(getString("name")),
    mainMuscle = requireNotNull(getString("mainMuscle")),
    otherMuscles = get("otherMuscles") as List<String>? ?: emptyList(),
    exercises = exercises.awaitAll(),
  )
}

private fun DocumentSnapshot.toExercise(id: String): Exercise {
  return Exercise(
    id = id,
    name = requireNotNull(getString("name")),
    equipment = requireNotNull(getString("equipment")),
    muscle = requireNotNull(getString("muscle")),
  )
}
