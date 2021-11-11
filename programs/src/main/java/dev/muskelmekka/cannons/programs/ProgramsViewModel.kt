package dev.muskelmekka.cannons.programs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.muskelmekka.cannons.programs.models.Program
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProgramsViewModel @Inject constructor(
  private val repository: ProgramsRepository,
) : ViewModel() {
  var isLoading: Boolean by mutableStateOf(false)
    private set

  var programs: List<Program>? by mutableStateOf(null)
    private set

  init {
    loadPrograms()
  }

  private fun loadPrograms() {
    viewModelScope.launch {
      isLoading = true

      programs = repository.getPrograms()

      isLoading = false
    }
  }
}
