package dev.muskelmekka.cannons.core.ui.insetsui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NavigationBar(
  modifier: Modifier = Modifier,
  contentPadding: PaddingValues = PaddingValues(0.dp),
  containerColor: Color = MaterialTheme.colorScheme.surface,
  contentColor: Color = contentColorFor(containerColor),
  tonalElevation: Dp = 3.dp,
  content: @Composable RowScope.() -> Unit,
) {
  Surface(
    color = containerColor,
    tonalElevation = tonalElevation,
    modifier = modifier
  ) {
    NavigationBar(
      containerColor = Color.Transparent,
      contentColor = contentColor,
      tonalElevation = 0.dp,
      modifier = Modifier.padding(contentPadding),
      content = content
    )
  }
}
