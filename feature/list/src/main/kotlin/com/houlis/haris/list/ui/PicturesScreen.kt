package com.houlis.haris.list.ui

import androidx.annotation.StringRes
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.houlis.haris.list.domain.Picture
import com.houlis.haris.list.ui.PicturesUiState.Type
import com.houlis.haris.network.feature.list.R

@Composable
internal fun PicturesRoute(modifier: Modifier = Modifier, viewModel: PicturesViewModel = hiltViewModel()) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    PicturesScreen(uiState, modifier) {
        viewModel.searchFor(it)
    }
}

@Composable
internal fun PicturesScreen(state: PicturesUiState, modifier: Modifier = Modifier, onValueChange: (String) -> Unit) {
    Column {
        Spacer(Modifier.height(16.dp))
        SearchBar(state.input, onValueChange = onValueChange)

        when (val uiState = state.type) {
            Type.Initial -> InfoItem(R.string.init_message)
            Type.Empty -> InfoItem(R.string.empty_list)
            Type.Error -> InfoItem(R.string.error)
            Type.Loading -> Loading()
            is Type.Fetched -> PicturesList(uiState, modifier)
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
private fun PicturesList(uiState: Type.Fetched, modifier: Modifier = Modifier) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = uiState.pictures) { picture ->
            PictureItem(picture, modifier)
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
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.surface),
        placeholder = {
            Text(stringResource(id = R.string.placeholder))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
private fun PictureItem(picture: Picture, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        AsyncImage(
            model = picture.image.value,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .padding(end = 8.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Text(
            text = picture.title.value,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.CenterVertically),
        )

    }
}
