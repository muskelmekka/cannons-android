package dev.muskelmekka.cannons.profile

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import dev.muskelmekka.cannons.core.ui.insetsui.SmallTopAppBar
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
  val context = LocalContext.current
  val profile = viewModel.profile

  LaunchedEffect("view-effects") {
    viewModel.viewEffect.collect { viewEffect ->
      when (viewEffect) {
        is ProfileViewEffect.Finish -> (context as? Activity)?.finish()
      }
    }
  }

  Scaffold(
    topBar = {
      SmallTopAppBar(
        contentPadding = rememberInsetsPaddingValues(LocalWindowInsets.current.statusBars),
        title = { Text(stringResource(R.string.profile_screen_title)) },
        actions = {
          Image(
            modifier = Modifier
              .padding(end = 16.dp)
              .size(32.dp)
              .clip(CircleShape),
            painter = rememberImagePainter(
              data = profile?.avatarUrl,
              builder = {
                placeholder(R.drawable.avatar_placeholder)
                fallback(R.drawable.avatar_placeholder)
                error(R.drawable.avatar_placeholder)
              },
            ),
            contentDescription = stringResource(R.string.cd_profile_header_avatar),
          )
        },
      )
    },
  ) { contentPadding ->
    if (profile != null) {
      LazyColumn(contentPadding = contentPadding) {
        item {
          Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
          ) {
            Text(
              modifier = Modifier.weight(1f),
              text = profile.displayName ?: stringResource(R.string.profile_header_anonymous),
              maxLines = 1,
              overflow = TextOverflow.Ellipsis,
              style = MaterialTheme.typography.headlineMedium,
            )

            OutlinedButton(onClick = viewModel::onSignOutButtonClicked) {
              Text(stringResource(R.string.profile_sign_out_button_label))
            }
          }
        }
      }
    }
  }
}
