package dev.muskelmekka.cannons.app.main

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.BottomNavigation
import com.google.accompanist.insets.ui.Scaffold
import dev.muskelmekka.cannons.app.R
import dev.muskelmekka.cannons.dna.AppTheme
import dev.muskelmekka.cannons.home.HomeScreen

@Composable
fun MainScreen() {
  AppTheme {
    Scaffold(
      bottomBar = {
        BottomNavigation(contentPadding = rememberInsetsPaddingValues(LocalWindowInsets.current.navigationBars)) {
          BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text(stringResource(R.string.bottom_nav_item_label_home)) },
            selected = true,
            onClick = {},
          )

          BottomNavigationItem(
            icon = { Icon(Icons.Default.History, contentDescription = null) },
            label = { Text(stringResource(R.string.bottom_nav_item_label_history)) },
            selected = false,
            onClick = {},
          )

          BottomNavigationItem(
            icon = { Icon(Icons.Default.CalendarToday, contentDescription = null) },
            label = { Text(stringResource(R.string.bottom_nav_item_label_programs)) },
            selected = false,
            onClick = {},
          )

          BottomNavigationItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text(stringResource(R.string.bottom_nav_item_label_profile)) },
            selected = false,
            onClick = {},
          )
        }
      },
    ) {
      HomeScreen()
    }
  }
}
