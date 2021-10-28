package dev.muskelmekka.cannons.core.ui.insetsui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Card(
  modifier: Modifier = Modifier,
  shape: Shape = RoundedCornerShape(12.dp),
  backgroundColor: Color = MaterialTheme.colorScheme.surface,
  contentColor: Color = contentColorFor(backgroundColor),
  border: BorderStroke? = null,
  elevation: Dp = 1.dp,
  content: @Composable () -> Unit,
) {
  Surface(
    modifier = modifier,
    shape = shape,
    color = backgroundColor,
    contentColor = contentColor,
    tonalElevation = elevation,
    border = border,
    content = content,
  )
}
