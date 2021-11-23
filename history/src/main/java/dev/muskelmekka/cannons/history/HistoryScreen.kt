@file:OptIn(ExperimentalTime::class)

package dev.muskelmekka.cannons.history

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.rememberInsetsPaddingValues
import dev.muskelmekka.cannons.core.ui.insetsui.SmallTopAppBar
import dev.muskelmekka.cannons.dna.AppTheme
import dev.muskelmekka.cannons.history.models.CompletedExercise
import dev.muskelmekka.cannons.history.models.CompletedSet
import dev.muskelmekka.cannons.history.models.Kg
import dev.muskelmekka.cannons.history.models.RecentWorkout
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@Composable
fun HistoryScreen(viewModel: HistoryViewModel = hiltViewModel()) {
  val recentWorkouts = viewModel.recentWorkouts

  HistoryScreen(recentWorkouts)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HistoryScreen(recentWorkouts: List<RecentWorkout>?) {
  Scaffold(
    topBar = {
      SmallTopAppBar(
        contentPadding = rememberInsetsPaddingValues(LocalWindowInsets.current.statusBars),
        title = { Text(stringResource(R.string.history_screen_title)) },
      )
    },
    bottomBar = { Spacer(Modifier.navigationBarsHeight()) },
  ) { contentPadding ->
    if (recentWorkouts != null) {
      RecentWorkoutsList(contentPadding, recentWorkouts)
    }
  }
}

@Composable
private fun RecentWorkoutsList(contentPadding: PaddingValues, workouts: List<RecentWorkout>) {
  LazyColumn(contentPadding = contentPadding) {
    items(workouts) { recentWorkout ->
      RecentWorkoutItem(recentWorkout, onClick = {})
    }
  }
}

@Composable
private fun RecentWorkoutItem(workout: RecentWorkout, onClick: () -> Unit) {
  Row(
    Modifier
      .fillMaxWidth()
      .clickable(onClick = onClick)
      .padding(16.dp),
  ) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
      Text(workout.name, style = MaterialTheme.typography.bodyLarge)
      Text(
        text = "${workout.startedAt.formatLong()}, ${workout.duration.toMinutesString()}",
        style = MaterialTheme.typography.bodyMedium,
      )
    }
  }
}

@Preview("Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
  val mockExercises = listOf(
    CompletedExercise(
      name = "Barbell Bicep Curls",
      equipment = "Barbell",
      sets = listOf(
        CompletedSet(30.kg, repCount = 12),
        CompletedSet(35.kg, repCount = 10),
        CompletedSet(40.kg, repCount = 8),
      ),
      muscle = "Bicep",
    ),
  )
  val recentWorkouts = listOf(
    RecentWorkout(
      id = "squats-for-the-thots",
      name = "Squats for the Thots",
      startedAt = Instant.parse("2021-05-10T12:00:00Z"),
      endedAt = Instant.parse("2021-05-10T13:45:00Z"),
      exercises = mockExercises,
    ),
    RecentWorkout(
      id = "rows-for-the-hoes",
      name = "Rows for the Hoes",
      startedAt = Instant.parse("2021-02-05T12:00:00Z"),
      endedAt = Instant.parse("2021-02-05T12:45:00Z"),
      exercises = mockExercises,
    ),
    RecentWorkout(
      id = "armageddon",
      name = "Armageddon",
      startedAt = Instant.parse("2021-01-01T12:00:00Z"),
      endedAt = Instant.parse("2021-01-01T12:45:00Z"),
      exercises = mockExercises,
    ),
  )

  AppTheme {
    HistoryScreen(recentWorkouts = recentWorkouts)
  }
}

@Composable
private fun Instant.formatLong(): String {
  val formatter = DateTimeFormatter.ofPattern(stringResource(R.string.date_time_format_long))
  val localDateTime = toLocalDateTime(TimeZone.currentSystemDefault()).toJavaLocalDateTime()

  return formatter.format(localDateTime)
}

@Composable
private fun Duration.toMinutesString(): String {
  return stringResource(R.string.history_recent_workout_item_duration, inWholeMinutes)
}

private val Int.kg: Kg get() = Kg(this.toFloat())
