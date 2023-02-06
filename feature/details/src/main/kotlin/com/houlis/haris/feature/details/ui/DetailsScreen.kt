package com.houlis.haris.feature.details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

@Composable
fun DetailsRoute(
    picId: String,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
    onBackClicked: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (val state = uiState) {
        DetailsUiState.Loading -> Loading(modifier)
        is DetailsUiState.Fetched -> DetailsScreen(state, modifier, onBackClicked)
    }

    viewModel.getImageFor(picId)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(uiState: DetailsUiState.Fetched, modifier: Modifier = Modifier, onBackClicked: () -> Unit) {
    AlertDialog(
        onDismissRequest = onBackClicked,
        modifier = modifier
    ) {
        AsyncImage(
            model = uiState.image.value,
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

