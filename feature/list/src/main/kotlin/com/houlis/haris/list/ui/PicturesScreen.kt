package com.houlis.haris.list.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.houlis.haris.list.domain.Picture

@Composable
internal fun PicturesRoute(modifier: Modifier = Modifier, viewModel: PicturesViewModel = hiltViewModel()) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    PicturesScreen(uiState, modifier)
}

@Composable
internal fun PicturesScreen(uiState: PicturesUiState, modifier: Modifier = Modifier) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        when (uiState) {
            PicturesUiState.Empty -> {}
            PicturesUiState.Error -> {}
            is PicturesUiState.Fetched -> {
                items(items = uiState.pictures) { picture ->
                    PictureItem(picture, modifier)
                }
            }
            PicturesUiState.Loading -> {}
        }
    }
}

@Composable
private fun PictureItem(picture: Picture, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        AsyncImage(
            model = picture.image.value,
            contentDescription = null
        )
        Text(text = picture.title.value)
    }
}
