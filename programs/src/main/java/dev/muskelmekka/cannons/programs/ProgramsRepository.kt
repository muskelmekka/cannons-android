package dev.muskelmekka.cannons.programs

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
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

class ProgramsRepository @Inject constructor(private val firestore: FirebaseFirestore) {
  suspend fun getPrograms(): List<Program> = withContext(Dispatchers.IO) {
    val snapshot = firestore.collection("programs").get().await()

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
  val exercisesSnapshot = reference.collection("exercises").get().await()
  val exercises = exercisesSnapshot.documents.map { async { it.toExercise(it.id) } }

  Workout(
    id = id,
    name = requireNotNull(getString("name")),
    mainMuscle = requireNotNull(getString("mainMuscle")),
    otherMuscles = get("otherMuscles") as List<String>? ?: emptyList(),
    exercises = exercises.awaitAll(),
  )
}

private suspend fun DocumentSnapshot.toExercise(id: String): Exercise {
  val documentReference = requireNotNull(getDocumentReference("exerciseRef"))
  val exerciseSnapshot = documentReference.get().await()

  return Exercise(
    id = id,
    name = requireNotNull(exerciseSnapshot.getString("name")),
    equipment = requireNotNull(exerciseSnapshot.getString("equipment")),
    muscle = requireNotNull(exerciseSnapshot.getString("muscle")),
  )
}
