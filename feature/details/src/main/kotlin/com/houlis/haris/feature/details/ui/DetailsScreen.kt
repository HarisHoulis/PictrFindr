package com.houlis.haris.feature.details.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.houlis.haris.feature.details.R
import com.houlis.haris.core.design.R as DesignR

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
    val s = rememberUpdatedState(newValue = picId)
    LaunchedEffect(s) {
        viewModel.getImageFor(picId)
    }
}

@Composable
fun DetailsScreen(uiState: DetailsUiState.Fetched, modifier: Modifier = Modifier, onBackClicked: () -> Unit) {
    Column(modifier = modifier) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clickable(onClick = onBackClicked)
                .padding(dimensionResource(DesignR.dimen.padding_4x))
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = uiState.image.large,
                contentDescription = stringResource(R.string.large_image_ctd),
                modifier = Modifier
                    .size(dimensionResource(DesignR.dimen.large_image_size))
            )
        }
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
