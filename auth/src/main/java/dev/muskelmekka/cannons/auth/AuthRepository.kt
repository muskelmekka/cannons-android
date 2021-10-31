package dev.muskelmekka.cannons.auth

import com.google.firebase.auth.FirebaseAuth
import dev.muskelmekka.cannons.auth.models.User
import javax.inject.Inject

class AuthRepository @Inject constructor(
  private val firebaseAuth: FirebaseAuth,
) {
  fun requireCurrentUser(): User {
    val firebaseUser = checkNotNull(firebaseAuth.currentUser) { "Not signed in." }
    val userInfo = firebaseUser.providerData.firstOrNull()

    return User(
      uid = firebaseUser.uid,
      photoUrl = userInfo?.photoUrl.toString(),
      displayName = userInfo?.displayName,
      emailAddress = userInfo?.email,
      phoneNumber = userInfo?.phoneNumber,
      isEmailVerified = userInfo?.isEmailVerified == true,
    )
  }

  fun signOut() {
    firebaseAuth.signOut()
  }
}
