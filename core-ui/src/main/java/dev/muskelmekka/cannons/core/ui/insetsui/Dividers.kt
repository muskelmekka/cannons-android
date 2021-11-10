package dev.muskelmekka.cannons.core.ui.insetsui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Divider(modifier: Modifier = Modifier) {
  Box(
    modifier
      .fillMaxWidth()
      .height(1.dp)
      .background(MaterialTheme.colorScheme.onSurface.copy(alpha = DividerAlpha)),
  )
}

private const val DividerAlpha = 0.12f
