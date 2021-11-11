package dev.muskelmekka.cannons.app.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import dev.muskelmekka.cannons.programs.ProgramsScreen

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
  val state = rememberMainScreenState(rememberAnimatedNavController())

  AppTheme {
    Scaffold(
      bottomBar = {
        NavigationBar(contentPadding = rememberInsetsPaddingValues(LocalWindowInsets.current.navigationBars)) {
          NavigationBarItem(
            icon = {
              if (state.navigation.currentPage == MainScreenNavigationState.Page.Home) {
                Icon(Icons.Filled.Home, contentDescription = null)
              } else {
                Icon(Icons.Outlined.Home, contentDescription = null)
              }
            },
            label = { Text(stringResource(R.string.bottom_nav_item_label_home)) },
            selected = state.navigation.currentPage == MainScreenNavigationState.Page.Home,
            onClick = { state.navigation.navigateTo(MainScreenNavigationState.Page.Home) },
          )

          NavigationBarItem(
            icon = {
              if (state.navigation.currentPage == MainScreenNavigationState.Page.History) {
                Icon(Icons.Filled.History, contentDescription = null)
              } else {
                Icon(Icons.Outlined.History, contentDescription = null)
              }
            },
            label = { Text(stringResource(R.string.bottom_nav_item_label_history)) },
            selected = state.navigation.currentPage == MainScreenNavigationState.Page.History,
            onClick = { state.navigation.navigateTo(MainScreenNavigationState.Page.History) },
          )

          NavigationBarItem(
            icon = {
              if (state.navigation.currentPage == MainScreenNavigationState.Page.Programs) {
                Icon(Icons.Filled.CalendarToday, contentDescription = null)
              } else {
                Icon(Icons.Outlined.CalendarToday, contentDescription = null)
              }
            },
            label = { Text(stringResource(R.string.bottom_nav_item_label_programs)) },
            selected = state.navigation.currentPage == MainScreenNavigationState.Page.Programs,
            onClick = { state.navigation.navigateTo(MainScreenNavigationState.Page.Programs) },
          )

          NavigationBarItem(
            icon = {
              if (state.navigation.currentPage == MainScreenNavigationState.Page.Profile) {
                Icon(Icons.Filled.Person, contentDescription = null)
              } else {
                Icon(Icons.Outlined.Person, contentDescription = null)
              }
            },
            label = { Text(stringResource(R.string.bottom_nav_item_label_profile)) },
            selected = state.navigation.currentPage == MainScreenNavigationState.Page.Profile,
            onClick = { state.navigation.navigateTo(MainScreenNavigationState.Page.Profile) },
          )
        }
      },
    ) {
      AnimatedNavHost(
        navController = state.navigation.navHostController,
        startDestination = MainScreenNavigationState.Page.Home.route,
      ) {
        composable(MainScreenNavigationState.Page.Home.route) {
          HomeScreen()
        }

        composable(MainScreenNavigationState.Page.History.route) {
          Box(Modifier.fillMaxSize())
        }

        composable(MainScreenNavigationState.Page.Programs.route) {
          ProgramsScreen()
        }

        composable(MainScreenNavigationState.Page.Profile.route) {
          ProfileScreen()
        }
      }
    }
  }
}
