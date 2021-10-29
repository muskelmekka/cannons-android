package dev.muskelmekka.cannons.auth.models

data class User(
  val uid: String?,
  val photoUrl: String?,
  val displayName: String?,
  val emailAddress: String?,
  val phoneNumber: String?,
  val isEmailVerified: Boolean,
)
