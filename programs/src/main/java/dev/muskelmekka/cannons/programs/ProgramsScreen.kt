package dev.muskelmekka.cannons.programs

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import dev.muskelmekka.cannons.core.ui.insetsui.Card
import dev.muskelmekka.cannons.core.ui.insetsui.Divider
import dev.muskelmekka.cannons.core.ui.insetsui.SmallTopAppBar
import dev.muskelmekka.cannons.dna.AppTheme
import dev.muskelmekka.cannons.programs.models.Exercise
import dev.muskelmekka.cannons.programs.models.Program
import dev.muskelmekka.cannons.programs.models.Workout

@Composable
fun ProgramsScreen(viewModel: ProgramsViewModel = hiltViewModel()) {
  val programs = viewModel.programs
  val isLoading = viewModel.isLoading

  ProgramsScreen(programs, isLoading = isLoading)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProgramsScreen(programs: List<Program>?, isLoading: Boolean) {
  Scaffold(
    topBar = {
      SmallTopAppBar(
        contentPadding = rememberInsetsPaddingValues(LocalWindowInsets.current.statusBars),
        title = { Text(stringResource(R.string.programs_screen_title)) },
      )
    },
  ) { contentPadding ->
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = contentPadding,
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      item { Spacer(Modifier.height(0.dp)) }

      if (isLoading) {
        items(3) { Placeholder() }
      } else if (programs != null) {
        items(programs) { ProgramCard(program = it) }
      }

      item { Spacer(Modifier.height(0.dp)) }
    }
  }
}

@Composable
private fun ProgramCard(modifier: Modifier = Modifier, program: Program) {
  Card(
    modifier = modifier
      .padding(horizontal = 16.dp)
      .fillMaxWidth(),
  ) {
    Column(Modifier.padding(16.dp)) {
      Text(program.name, style = MaterialTheme.typography.titleLarge)

      Workouts(Modifier.padding(top = 8.dp), program.workouts)

      Divider(Modifier.padding(top = 16.dp))

      CardActions(Modifier.padding(top = 8.dp))
    }
  }
}

@Composable
private fun Workouts(modifier: Modifier = Modifier, workouts: List<Workout>) {
  Column(modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
    for (workout in workouts) {
      val numberOfOtherMuscles = workout.otherMuscles.size

      val nameAndWorkoutsText = if (numberOfOtherMuscles == 0) {
        stringResource(R.string.programs_card_workout_name_with_only_main_muscle, workout.name, workout.mainMuscle)
      } else {
        LocalContext.current.resources.getQuantityString(
          R.plurals.programs_card_workout_name_with_main_muscle_and_others,
          numberOfOtherMuscles,
          workout.name,
          workout.mainMuscle,
          numberOfOtherMuscles,
        )
      }

      Text(nameAndWorkoutsText, style = MaterialTheme.typography.bodySmall)
    }
  }
}

@Composable
private fun CardActions(modifier: Modifier = Modifier) {
  Row(modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
    TextButton(onClick = {}) {
      Text(stringResource(R.string.programs_card_start_now_button))
    }

    TextButton(onClick = {}) {
      Text(stringResource(R.string.programs_card_show_details_button))
    }
  }
}

@Preview("Program Card, Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview("Program Card, Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProgramCardPreview() {
  val mockProgram = Program(
    id = "pain-and-gain",
    name = "Pain & Gain",
    workouts = listOf(

      Workout(
        id = "armageddon",
        name = "Armageddon",
        mainMuscle = "Biceps",
        otherMuscles = emptyList(),
        exercises = listOf(
          Exercise(
            id = "barbell-bicep-curls",
            name = "Barbell Bicep Curls",
            muscle = "Biceps",
            equipment = "Barbell",
          ),
          Exercise(
            id = "wide-grip-standing-barbell-curl",
            name = "Wide-Grip Standing Barbell Curl",
            muscle = "Biceps",
            equipment = "Barbell",
          ),
          Exercise(
            id = "hammer-curls",
            name = "Hammer Curls",
            muscle = "Biceps",
            equipment = "Dumbbell",
          ),
        ),
      ),
      Workout(
        id = "skuldre-for-huldre",
        name = "Skuldre for Huldre",
        mainMuscle = "Shoulders",
        otherMuscles = listOf("Biceps"),
        exercises = listOf(
          Exercise(
            id = "clean-and-press",
            name = "Clean and Press",
            muscle = "Shoulders",
            equipment = "Barbell",
          ),
          Exercise(
            id = "push-press",
            name = "Push Press",
            muscle = "Shoulders",
            equipment = "Barbell",
          ),
          Exercise(
            id = "standing-palm-in-one-arm-dumbbell-press",
            name = "Standing Palm-In One-Arm Dumbbell Press",
            muscle = "Shoulders",
            equipment = "Dumbbell",
          ),
        ),
      ),
    ),
  )

  AppTheme {
    Surface(color = MaterialTheme.colorScheme.background) {
      ProgramCard(modifier = Modifier.padding(16.dp), program = mockProgram)
    }
  }
}

@Composable
private fun Placeholder(modifier: Modifier = Modifier) {
  Box(
    modifier = modifier
      .padding(horizontal = 16.dp)
      .fillMaxWidth()
      .height(190.dp)
      .placeholder(
        visible = true,
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .1f),
        highlight = PlaceholderHighlight.fade(MaterialTheme.colorScheme.onBackground.copy(alpha = .2f)),
      ),
  )
}

@Preview("Program Card Placeholder, Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview("Program Card Placeholder, Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PlaceholderPreview() {
  AppTheme {
    Surface(color = MaterialTheme.colorScheme.background) {
      Placeholder(Modifier.padding(vertical = 16.dp))
    }
  }
}

@Preview("Screen, Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview("Screen, Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreview() {
  val mockPrograms = listOf(
    Program(
      id = "pain-and-gain",
      name = "Pain & Gain",
      workouts = listOf(

        Workout(
          id = "armageddon",
          name = "Armageddon",
          mainMuscle = "Biceps",
          otherMuscles = emptyList(),
          exercises = listOf(
            Exercise(
              id = "barbell-bicep-curls",
              name = "Barbell Bicep Curls",
              muscle = "Biceps",
              equipment = "Barbell",
            ),
            Exercise(
              id = "wide-grip-standing-barbell-curl",
              name = "Wide-Grip Standing Barbell Curl",
              muscle = "Biceps",
              equipment = "Barbell",
            ),
            Exercise(
              id = "hammer-curls",
              name = "Hammer Curls",
              muscle = "Biceps",
              equipment = "Dumbbell",
            ),
          ),
        ),
        Workout(
          id = "skuldre-for-huldre",
          name = "Skuldre for Huldre",
          mainMuscle = "Shoulders",
          otherMuscles = listOf("Biceps"),
          exercises = listOf(
            Exercise(
              id = "clean-and-press",
              name = "Clean and Press",
              muscle = "Shoulders",
              equipment = "Barbell",
            ),
            Exercise(
              id = "push-press",
              name = "Push Press",
              muscle = "Shoulders",
              equipment = "Barbell",
            ),
            Exercise(
              id = "standing-palm-in-one-arm-dumbbell-press",
              name = "Standing Palm-In One-Arm Dumbbell Press",
              muscle = "Shoulders",
              equipment = "Dumbbell",
            ),
          ),
        ),
      ),
    ),
    Program(
      id = "get-fit-or-try-dying",
      name = "Get Fit or Try Dying",
      workouts = listOf(
        Workout(
          id = "armageddon",
          name = "Armageddon",
          mainMuscle = "Biceps",
          otherMuscles = emptyList(),
          exercises = listOf(
            Exercise(
              id = "barbell-bicep-curls",
              name = "Barbell Bicep Curls",
              muscle = "Biceps",
              equipment = "Barbell",
            ),
            Exercise(
              id = "wide-grip-standing-barbell-curl",
              name = "Wide-Grip Standing Barbell Curl",
              muscle = "Biceps",
              equipment = "Barbell",
            ),
            Exercise(
              id = "hammer-curls",
              name = "Hammer Curls",
              muscle = "Biceps",
              equipment = "Dumbbell",
            ),
          ),
        ),
        Workout(
          id = "skuldre-for-huldre",
          name = "Skuldre for Huldre",
          mainMuscle = "Shoulders",
          otherMuscles = listOf("Biceps"),
          exercises = listOf(
            Exercise(
              id = "clean-and-press",
              name = "Clean and Press",
              muscle = "Shoulders",
              equipment = "Barbell",
            ),
            Exercise(
              id = "push-press",
              name = "Push Press",
              muscle = "Shoulders",
              equipment = "Barbell",
            ),
            Exercise(
              id = "standing-palm-in-one-arm-dumbbell-press",
              name = "Standing Palm-In One-Arm Dumbbell Press",
              muscle = "Shoulders",
              equipment = "Dumbbell",
            ),
          ),
        ),
      ),
    ),
  )

  AppTheme {
    ProgramsScreen(mockPrograms, isLoading = false)
  }
}
