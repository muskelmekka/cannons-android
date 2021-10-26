package dev.muskelmekka.cannons.home

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.insets.ui.TopAppBar
import dev.muskelmekka.cannons.dna.AppTheme

@Composable
fun HomeScreen() {
  Scaffold(
    topBar = {
      TopAppBar(
        contentPadding = rememberInsetsPaddingValues(LocalWindowInsets.current.statusBars),
        title = { Text(stringResource(R.string.home_screen_title)) },
      )
    },
  ) { contentPadding ->
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = contentPadding,
      verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {
      item { SectionHeader(title = stringResource(R.string.home_section_header_next_up)) }

      item { NextWorkoutCard(modifier = Modifier.fillParentMaxWidth()) }

      item { SectionHeader(title = stringResource(R.string.home_section_header_recent_workouts)) }

      item { Spacer(Modifier.height(8.dp)) }

      items(3) { RecentWorkoutItem() }
    }
  }
}

@Composable
private fun SectionHeader(modifier: Modifier = Modifier, title: String) {
  Text(
    text = title.uppercase(),
    modifier = modifier.padding(start = 16.dp, top = 32.dp, end = 16.dp),
    style = MaterialTheme.typography.overline,
  )
}

@Composable
private fun NextWorkoutCard(modifier: Modifier = Modifier) {
  Card(modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)) {
    Column(Modifier.padding(16.dp)) {
      Text("Arm Day", style = MaterialTheme.typography.h5)

      Text(
        text = "Every day is arm day, and this one is no different. Lorem ipsum dolor sit amet.",
        modifier = Modifier.padding(top = 8.dp),
        style = MaterialTheme.typography.body2,
      )

      Divider(Modifier.padding(top = 16.dp))

      Row(Modifier.padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        TextButton(onClick = {}) {
          Text(stringResource(R.string.home_next_up_card_start_now_button))
        }

        TextButton(onClick = {}) {
          Text(stringResource(R.string.home_next_up_card_show_details_button))
        }
      }
    }
  }
}

@Composable
private fun RecentWorkoutItem() {
  Row(
    modifier = Modifier
      .clickable(onClick = {})
      .padding(16.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Column(Modifier.weight(1f)) {
      Text("Arm Day")
      Text("Yesterday", style = MaterialTheme.typography.body2)
    }

    Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
  }
}

@Preview("Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview("Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
  AppTheme {
    HomeScreen()
  }
}
