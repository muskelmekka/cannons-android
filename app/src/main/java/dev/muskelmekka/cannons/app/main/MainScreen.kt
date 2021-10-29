package dev.muskelmekka.cannons.app.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.muskelmekka.cannons.app.R
import dev.muskelmekka.cannons.core.ui.insetsui.NavigationBar
import dev.muskelmekka.cannons.dna.AppTheme
import dev.muskelmekka.cannons.home.HomeScreen
import dev.muskelmekka.cannons.profile.ProfileScreen

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
  AppTheme {
    val navController = rememberAnimatedNavController()

    Scaffold(
      bottomBar = {
        NavigationBar(contentPadding = rememberInsetsPaddingValues(LocalWindowInsets.current.navigationBars)) {
          NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text(stringResource(R.string.bottom_nav_item_label_home)) },
            selected = true,
            onClick = {},
          )

          NavigationBarItem(
            icon = { Icon(Icons.Outlined.History, contentDescription = null) },
            label = { Text(stringResource(R.string.bottom_nav_item_label_history)) },
            selected = false,
            onClick = {},
          )

          NavigationBarItem(
            icon = { Icon(Icons.Outlined.CalendarToday, contentDescription = null) },
            label = { Text(stringResource(R.string.bottom_nav_item_label_programs)) },
            selected = false,
            onClick = {},
          )

          NavigationBarItem(
            icon = { Icon(Icons.Outlined.Person, contentDescription = null) },
            label = { Text(stringResource(R.string.bottom_nav_item_label_profile)) },
            selected = false,
            onClick = {},
          )
        }
      },
    ) {
      AnimatedNavHost(navController, startDestination = "/home") {
        composable("/home") {
          HomeScreen()
        }

        composable("/profile") {
          ProfileScreen()
        }
      }
    }
  }
}
