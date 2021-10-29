package dev.muskelmekka.cannons.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import dev.muskelmekka.cannons.core.ui.insetsui.SmallTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
  val profile = viewModel.profile

  Scaffold(
    topBar = {
      SmallTopAppBar(
        contentPadding = rememberInsetsPaddingValues(LocalWindowInsets.current.statusBars),
        title = { Text(stringResource(R.string.profile_screen_title)) },
        actions = {
          AnimatedVisibility(profile != null) {
            IconButton(modifier = Modifier.padding(end = 16.dp), onClick = { }) {
              Image(
                modifier = Modifier
                  .size(32.dp)
                  .clip(CircleShape),
                painter = rememberImagePainter(
                  data = null,
                  builder = {
                    placeholder(R.drawable.avatar_placeholder)
                    fallback(R.drawable.avatar_placeholder)
                    error(R.drawable.avatar_placeholder)
                  },
                ),
                contentDescription = stringResource(R.string.cd_profile_header_avatar),
              )
            }
          }
        },
      )
    },
  ) { contentPadding ->
    if (profile != null) {
      LazyColumn(contentPadding = contentPadding) {
        item {
          Text(
            modifier = Modifier.padding(16.dp),
            text = profile.displayName ?: stringResource(R.string.profile_header_anonymous),
            style = MaterialTheme.typography.headlineMedium,
          )
        }
      }
    }
  }
}
