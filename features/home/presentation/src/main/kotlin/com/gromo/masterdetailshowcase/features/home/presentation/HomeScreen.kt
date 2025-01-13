package com.gromo.masterdetailshowcase.features.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gromo.masterdetailshowcase.features.home.presentation.models.CharacterUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeViewStateUiModel
import kotlinx.collections.immutable.PersistentList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.onViewInitialised()
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        HomeContent(viewState = viewState, innerPadding = innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(viewState: HomeViewStateUiModel, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Rick & Morty Master details showcase",
            modifier = Modifier.padding(16.dp)
        )

        PullToRefreshBox(
            isRefreshing = viewState.isRefreshing,
            onRefresh = viewState.onRefreshTriggered,
            modifier = Modifier.fillMaxWidth(),
        ) {
            when (viewState) {
                is HomeViewStateUiModel.Empty -> HomeContentEmpty()
                is HomeViewStateUiModel.Error -> HomeContentError()
                is HomeViewStateUiModel.Filled -> HomeContentFilled(characters = viewState.characters)
            }
        }

    }
}

@Composable
fun HomeContentFilled(
    characters: PersistentList<CharacterUiModel>,
) {
    val gridState: LazyGridState = rememberLazyGridState()
    val requiredCellSize =
        (LocalConfiguration.current.screenWidthDp.toFloat() / 2f) - 24.dp.value

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        columns = GridCells.Adaptive(requiredCellSize.dp),
        state = gridState
    ) {
        items(items = characters, key = { it.id }) { character ->
            Text(
                text = character.name,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { character.onClick(character.id) }
            )
        }
    }
}

@Composable
fun HomeContentEmpty() {
    Text(
        text = "Empty",
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}

@Composable
fun HomeContentError() {
    Text(
        text = "Error",
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}
