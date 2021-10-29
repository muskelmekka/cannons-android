package dev.muskelmekka.cannons.profile

import dev.muskelmekka.cannons.auth.AuthRepository
import dev.muskelmekka.cannons.profile.models.Profile
import javax.inject.Inject

class ProfileRepository @Inject constructor(
  private val authRepository: AuthRepository,
) {
  fun getMyProfile(): Profile {
    val user = authRepository.requireCurrentUser()

    return Profile(
      avatarUrl = user.photoUrl,
      displayName = user.displayName,
    )
  }
}
