package com.houlis.haris.picfind.feature.details.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.houlis.haris.picfind.core.domain.Picture.Image
import com.houlis.haris.picfind.feature.details.R
import com.houlis.haris.picfind.core.design.R as DesignR

@Composable
internal fun DetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
    onBackClicked: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    state.pic?.let { pic ->
        DetailsScreen(pic, modifier, onBackClicked)
    } ?: Loading(modifier)
}

@Composable
private fun DetailsScreen(pic: Image, modifier: Modifier = Modifier, onBackClicked: () -> Unit) {
    Column(modifier = modifier) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
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
                model = pic.large,
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
