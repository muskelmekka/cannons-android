package dev.muskelmekka.cannons.app.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import dev.muskelmekka.cannons.auth.AuthActivity
import dev.muskelmekka.cannons.auth.isSignedIn

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowCompat.setDecorFitsSystemWindows(window, false)

    if (!isSignedIn()) {
      startActivity(Intent(this, AuthActivity::class.java))
      finish()
    } else {
      setContent {
        MainScreen()
      }
    }
  }
}
