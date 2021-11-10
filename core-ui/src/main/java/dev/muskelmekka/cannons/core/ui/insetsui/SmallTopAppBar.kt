package dev.muskelmekka.cannons.core.ui.insetsui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SmallTopAppBar(
  title: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  contentPadding: PaddingValues = PaddingValues(0.dp),
  navigationIcon: @Composable () -> Unit = {},
  actions: @Composable RowScope.() -> Unit = {},
  colors: TopAppBarColors = TopAppBarDefaults.smallTopAppBarColors(),
  scrollBehavior: TopAppBarScrollBehavior? = null,
) {
  Surface(
    color = colors.containerColor(0f).value,
    tonalElevation = 1.dp,
    modifier = modifier,
  ) {
    androidx.compose.material3.SmallTopAppBar(
      title = title,
      navigationIcon = navigationIcon,
      actions = actions,
      colors = colors,
      modifier = Modifier.padding(contentPadding),
      scrollBehavior = scrollBehavior,
    )
  }
}
