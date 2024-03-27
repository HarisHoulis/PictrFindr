package com.houlis.haris.picfind.feature.list.ui

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.houlis.haris.picfind.core.domain.Picture
import com.houlis.haris.picfind.feature.list.R
import com.houlis.haris.picfind.feature.list.ui.viewmodel.LoadState.Error
import com.houlis.haris.picfind.feature.list.ui.viewmodel.LoadState.Idle
import com.houlis.haris.picfind.feature.list.ui.viewmodel.LoadState.Loaded
import com.houlis.haris.picfind.feature.list.ui.viewmodel.LoadState.Loading
import com.houlis.haris.picfind.feature.list.ui.viewmodel.LoadState.NoResults
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesState
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesViewModel
import kotlinx.collections.immutable.ImmutableList
import com.houlis.haris.picfind.core.design.R as DesignR

@Composable
internal fun PicturesRoute(
    modifier: Modifier = Modifier,
    viewModel: PicturesViewModel = hiltViewModel(),
    navigateToDetails: (picId: String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    state.navigateToDetails?.let { pic ->
        navigateToDetails(pic.id)
    }

    PicturesScreen(
        state = state,
        modifier = modifier,
        onClick = viewModel::onPictureClicked,
        onValueChange = viewModel::searchFor
    )
}

@Composable
internal fun PicturesScreen(
    state: PicturesState,
    modifier: Modifier = Modifier,
    onClick: (Picture) -> Unit,
    onValueChange: (String) -> Unit,
) {
    with(state) {
        Column {
            Spacer(Modifier.height(dimensionResource(DesignR.dimen.spacer_height)))
            SearchBar(onValueChange)

            if (loadState != Loading) {
                LocalFocusManager.current.clearFocus()
            }

            when (loadState) {
                Idle -> InfoBox(R.string.init_message)
                NoResults -> InfoBox(R.string.empty_list)
                Error -> InfoBox(R.string.error)
                Loading -> Loading()
                Loaded -> Pictures(pictures, modifier, onClick)
            }
        }
    }
}

@Composable
private fun SearchBar(onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { newValue ->
            onValueChange(newValue)
            text = newValue
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_ctd)
            )
        },
        placeholder = {
            Text(stringResource(id = R.string.placeholder))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = dimensionResource(DesignR.dimen.search_bar_min_height))
    )
}

@Composable
private fun InfoBox(@StringRes text: Int, modifier: Modifier = Modifier) {
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
private fun Pictures(pictures: ImmutableList<Picture>, modifier: Modifier = Modifier, onClick: (Picture) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(DesignR.dimen.padding_4x)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(DesignR.dimen.padding_2x)),
        modifier = modifier
    ) {
        items(pictures, key = Picture::id) { picture ->
            PictureItem(picture, modifier, onClick)
        }
    }
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
