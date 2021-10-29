package dev.muskelmekka.cannons.app.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController

class MainScreenState(navHostController: NavHostController) {
  val navigation = MainScreenNavigationState(navHostController)
}

class MainScreenNavigationState(val navHostController: NavHostController) {
  var currentPage: Page by mutableStateOf(Page.Home)
    private set

  fun navigateTo(page: Page) {
    if (currentPage == page) return

    navHostController.popBackStack(currentPage.route, inclusive = true)
    navHostController.navigate(page.route)

    currentPage = page
  }

  enum class Page(val route: String) {
    Home("/home"),
    History("/history"),
    Programs("/programs"),
    Profile("/profile"),
  }
}

@Composable
fun rememberMainScreenState(navHostController: NavHostController): MainScreenState = MainScreenState(navHostController)
