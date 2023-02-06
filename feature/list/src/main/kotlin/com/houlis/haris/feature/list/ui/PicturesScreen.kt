package com.houlis.haris.feature.list.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.houlis.haris.core.domain.Picture
import com.houlis.haris.feature.list.R
import com.houlis.haris.feature.list.ui.PicturesUiState.Type
import com.houlis.haris.core.design.R as DesignR

@Composable
internal fun PicturesRoute(
    modifier: Modifier = Modifier,
    viewModel: PicturesViewModel = hiltViewModel(),
    navigateToDetails: (picId: String) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    uiState.navigateToDetails?.run {
        navigateToDetails(picId)
        viewModel.onNavigatedToDetails()
    }

    PicturesScreen(
        uiState,
        modifier,
        { viewModel.onPictureClicked(it) }
    ) {
        viewModel.searchFor(it)
    }
}

@Composable
internal fun PicturesScreen(
    state: PicturesUiState,
    modifier: Modifier = Modifier,
    onClick: (Picture) -> Unit,
    onValueChange: (String) -> Unit,
) {
    Column {
        Spacer(Modifier.height(dimensionResource(DesignR.dimen.spacer_height)))
        SearchBar(state.input, onValueChange = onValueChange)

        when (val uiState = state.type) {
            Type.Initial -> InfoItem(R.string.init_message)
            Type.Empty -> InfoItem(R.string.empty_list)
            Type.Error -> InfoItem(R.string.error)
            Type.Loading -> Loading()
            is Type.Fetched -> {
                LocalFocusManager.current.clearFocus()
                PicturesList(uiState, modifier, onClick)
            }
        }
    }
}

@Composable
private fun InfoItem(@StringRes text: Int, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = stringResource(text))
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

@Composable
private fun PicturesList(uiState: Type.Fetched, modifier: Modifier = Modifier, onClick: (Picture) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(DesignR.dimen.padding_4x)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(DesignR.dimen.padding_2x))
    ) {
        items(items = uiState.pictures) { picture ->
            PictureItem(picture, modifier, onClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_ctd)
            )
        },
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.surface),
        placeholder = {
            Text(stringResource(id = R.string.placeholder))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = dimensionResource(DesignR.dimen.search_bar_min_height))
    )
}

@Composable
private fun PictureItem(picture: Picture, modifier: Modifier = Modifier, onClick: (Picture) -> Unit) {
    Row(
        modifier = modifier.clickable {
            onClick(picture)
        }
    ) {
        AsyncImage(
            model = picture.image.thumbnail,
            contentDescription = stringResource(R.string.picture_item_ctd),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(dimensionResource(DesignR.dimen.thumbnail_image_size))
                .padding(end = dimensionResource(DesignR.dimen.padding_2x))
                .clip(MaterialTheme.shapes.small)
        )
        Text(
            text = picture.title.value,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
