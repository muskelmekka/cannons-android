package dev.muskelmekka.cannons.dna

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun AppTheme(isDarkMode: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
  val rippleIndication = rememberRipple()

  ProvideWindowInsets {
    CompositionLocalProvider(LocalIndication provides rippleIndication) {
      MaterialTheme(
        colorScheme = if (isDarkMode) appDarkColors else appLightColors,
        typography = appTypography,
        content = content,
      )
    }
  }
}

@Preview(name = "Theme in Light Mode")
@Composable
private fun LightModePreview() {
  AppTheme(isDarkMode = false) {
    PreviewContent()
  }
}

@Preview(name = "Theme in Dark Mode")
@Composable
private fun DarkModePreview() {
  AppTheme(isDarkMode = true) {
    PreviewContent()
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PreviewContent() {
  val scaffoldState = rememberScaffoldState()
  val coroutineScope = rememberCoroutineScope()

  var currentBottomNavTab by remember { mutableStateOf(0) }
  var isNotificationsEnabled by remember { mutableStateOf(true) }

  Scaffold(
    modifier = Modifier
      .systemBarsPadding()
      .fillMaxSize(),
    scaffoldState = scaffoldState,
    topBar = { SmallTopAppBar(title = { Text("Top App Bar") }) },
    bottomBar = {
      NavigationBar {
        NavigationBarItem(
          selected = currentBottomNavTab == 0,
          onClick = { currentBottomNavTab = 0 },
          icon = {
            if (currentBottomNavTab == 0) {
              Icon(Icons.Default.Home, contentDescription = null)
            } else {
              Icon(Icons.Outlined.Home, contentDescription = null)
            }
          },
          label = { Text("Home") },
        )

        NavigationBarItem(
          selected = currentBottomNavTab == 1,
          onClick = { currentBottomNavTab = 1 },
          icon = {
            if (currentBottomNavTab == 1) {
              Icon(Icons.Default.FitnessCenter, contentDescription = null)
            } else {
              Icon(Icons.Outlined.FitnessCenter, contentDescription = null)
            }
          },
          label = { Text("Gym") },
        )

        NavigationBarItem(
          selected = currentBottomNavTab == 2,
          onClick = { currentBottomNavTab = 2 },
          icon = {
            if (currentBottomNavTab == 2) {
              Icon(Icons.Default.Alarm, contentDescription = null)
            } else {
              Icon(Icons.Outlined.Alarm, contentDescription = null)
            }
          },
          label = { Text("Alarm") },
        )
        NavigationBarItem(
          selected = currentBottomNavTab == 3,
          onClick = { currentBottomNavTab = 3 },
          icon = {
            if (currentBottomNavTab == 3) {
              Icon(Icons.Default.Person, contentDescription = null)
            } else {
              Icon(Icons.Outlined.Person, contentDescription = null)
            }
          },
          label = { Text("Profile") },
        )
      }
    },
    floatingActionButton = {
      FloatingActionButton(onClick = {}) {
        Icon(Icons.Default.Add, contentDescription = null)
      }
    },
  ) {
    Surface(modifier = Modifier.padding(16.dp)) {
      Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Headline Medium", style = MaterialTheme.typography.headlineMedium)
        Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", style = MaterialTheme.typography.bodyMedium)

        Button(onClick = {}) { Text("Filled button (enabled)") }
        OutlinedButton(onClick = {}) { Text("Outlined button (enabled)") }
        TextButton(onClick = {}) { Text("Text button (enabled)") }

        Button(enabled = false, onClick = {}) { Text("Filled button (disabled)") }
        OutlinedButton(enabled = false, onClick = {}) { Text("Outlined button (disabled)") }
        TextButton(enabled = false, onClick = {}) { Text("Text button (disabled)") }
      }
    }
  }
}
