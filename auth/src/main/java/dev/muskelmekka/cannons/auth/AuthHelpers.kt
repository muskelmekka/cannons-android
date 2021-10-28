package dev.muskelmekka.cannons.auth

import com.google.firebase.auth.FirebaseAuth

fun isSignedIn(): Boolean = FirebaseAuth.getInstance().currentUser != null
