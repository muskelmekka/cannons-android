package dev.muskelmekka.cannons.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.muskelmekka.cannons.auth.AuthRepository
import dev.muskelmekka.cannons.profile.models.Profile
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
  private val repository: ProfileRepository,
  private val authRepository: AuthRepository,
) : ViewModel() {
  private val _viewEffect = Channel<ProfileViewEffect>()
  val viewEffect = _viewEffect.receiveAsFlow()

  var profile: Profile? by mutableStateOf(null)
    private set

  init {
    viewModelScope.launch {
      profile = repository.getMyProfile()
    }
  }

  fun onSignOutButtonClicked() {
    viewModelScope.launch {
      authRepository.signOut()

      _viewEffect.send(ProfileViewEffect.Finish)
    }
  }
}

sealed interface ProfileViewEffect {
  object Finish : ProfileViewEffect
}
