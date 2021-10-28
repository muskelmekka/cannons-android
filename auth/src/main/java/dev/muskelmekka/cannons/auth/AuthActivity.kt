package dev.muskelmekka.cannons.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.net.toUri
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult

class AuthActivity : ComponentActivity() {
  private val startFirebaseAuth = registerForActivityResult(FirebaseAuthUIActivityResultContract()) { result ->
    onSignInResult(result)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val identityProviders = listOf(
      AuthUI.IdpConfig.EmailBuilder().build(),
      AuthUI.IdpConfig.GoogleBuilder().build(),
    )

    val intent = AuthUI.getInstance()
      .createSignInIntentBuilder()
      .setTheme(R.style.Theme_App)
      .setAvailableProviders(identityProviders)
      .build()

    startFirebaseAuth.launch(intent)
  }

  private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
    val response = result.idpResponse

    if (result.resultCode == RESULT_OK) {
      startActivity(Intent(Intent.ACTION_VIEW, "cannons://main/landing".toUri()).apply { setPackage(packageName) })

      finish()
    } else {
      if (response == null) {
        // User pressed back button
        finish()
        return
      }
    }
  }
}
