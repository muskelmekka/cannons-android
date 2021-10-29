package dev.muskelmekka.cannons.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.muskelmekka.cannons.profile.models.Profile
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
  private val repository: ProfileRepository,
) : ViewModel() {
  var profile: Profile? by mutableStateOf(null)
    private set

  init {
    viewModelScope.launch {
      profile = repository.getMyProfile()
    }
  }
}
