package dev.muskelmekka.cannons.history

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.muskelmekka.cannons.history.models.RecentWorkout
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
  private val repository: HistoryRepository,
) : ViewModel() {
  var isLoading: Boolean by mutableStateOf(false)
    private set

  var recentWorkouts: List<RecentWorkout>? by mutableStateOf(null)
    private set

  init {
    loadRecentWorkouts()
  }

  private fun loadRecentWorkouts() {
    viewModelScope.launch {
      isLoading = true

      recentWorkouts = repository.getRecentWorkouts()

      isLoading = false
    }
  }
}
