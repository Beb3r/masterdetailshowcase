package com.gromo.masterdetailshowcase.features.home.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gromo.masterdetailshowcase.features.home.presentation.composables.HomeOnboarding
import com.gromo.masterdetailshowcase.features.home.presentation.models.CharacterUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.EpisodeUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeCharacterListViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeEpisodeListViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeOnboardingViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeTopBarActionDataUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeTopBarActionTypeUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeViewStateUiModel
import com.gromo.masterdetailshowcase.libraries.design.AppImage
import com.gromo.masterdetailshowcase.libraries.design.Spacing16
import com.gromo.masterdetailshowcase.libraries.design.Spacing24
import com.gromo.masterdetailshowcase.libraries.design.Spacing8
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.koin.androidx.compose.koinViewModel
import com.gromo.masterdetailshowcase.libraries.design.R.drawable as drawables
import com.gromo.masterdetailshowcase.libraries.translations.R.string as translations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val hazeState = remember { HazeState() }

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.onViewInitialised()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(translations.home_title),
                    )
                },
                actions = {
                    AnimatedContent(
                        targetState = viewState.topBarActionData,
                        transitionSpec = {
                            scaleIn() togetherWith scaleOut()
                        },
                        label = "toolbar action animation"
                    ) { targetState ->
                        IconButton(onClick = {
                            targetState.onClick(targetState.type)
                        }) {
                            Icon(
                                painter = painterResource(targetState.iconResId),
                                contentDescription = ""
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HomeContent(
                viewState = viewState,
                hazeState = hazeState
            )
            HomeOnboarding(
                viewState = viewState.onboardingViewState,
                hazeState = hazeState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(viewState: HomeViewStateUiModel, hazeState: HazeState) {
    Column(
        modifier = Modifier.hazeSource(state = hazeState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PullToRefreshBox(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Spacing8),
            isRefreshing = viewState.isRefreshing,
            onRefresh = viewState.onRefreshTriggered,
        ) {
            val gridState: LazyGridState = rememberLazyGridState()
            val requiredCellSize =
                (LocalConfiguration.current.screenWidthDp.toFloat() / 2f) - Spacing24.value

            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                columns = GridCells.Adaptive(requiredCellSize.dp),
                state = gridState,
            ) {
                homeEpisodeListContent(viewState.episodeListViewState)
                homeCharacterListContent(viewState.characterListViewState)
            }
        }
    }
}


fun LazyGridScope.homeEpisodeListContent(
    viewState: HomeEpisodeListViewStateUiModel
) {
    item(span = {
        GridItemSpan(maxLineSpan)
    }) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(start = Spacing16),
                text = stringResource(translations.home_episode_list_title),
                fontSize = 20.sp,
            )
            when (viewState) {
                is HomeEpisodeListViewStateUiModel.Filled ->
                    HomeEpisodeListContentFilled(
                        episodes = viewState.episodes
                    )

                is HomeEpisodeListViewStateUiModel.Empty -> HomeEpisodeListContentEmpty()
                is HomeEpisodeListViewStateUiModel.Error -> HomeEpisodeListContentError()
            }
        }
    }

}

@Composable
fun HomeEpisodeListContentFilled(
    episodes: PersistentList<EpisodeUiModel>,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(items = episodes, key = { it.id }) { episode ->
            Card(
                modifier = Modifier
                    .padding(Spacing8)
                    .fillMaxWidth(),
                onClick = {
                    episode.onClick(episode.id)
                }
            ) {
                Text(
                    modifier = Modifier.padding(Spacing16),
                    text = episode.name
                )
            }
        }
    }
}

@Composable
fun HomeEpisodeListContentEmpty() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing16),
        text = stringResource(translations.home_episode_list_empty),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun HomeEpisodeListContentError() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing16),
        text = stringResource(translations.home_episode_list_error),
        textAlign = TextAlign.Center,
    )
}

fun LazyGridScope.homeCharacterListContent(
    viewState: HomeCharacterListViewStateUiModel
) {
    item(span = {
        GridItemSpan(maxLineSpan)
    }) {
        Text(
            modifier = Modifier.padding(top = Spacing24, start = Spacing16),
            text = stringResource(translations.home_character_list_title),
            fontSize = 20.sp,
        )
    }
    when (viewState) {
        is HomeCharacterListViewStateUiModel.Filled ->
            items(
                items = viewState.characters,
                key = { it.id }) { character ->
                Card(
                    modifier = Modifier
                        .padding(Spacing8)
                        .fillMaxWidth(),
                    onClick = {
                        character.onClick(character.id)
                    }
                ) {
                    AppImage(
                        modifier = Modifier.fillMaxWidth(),
                        contentDescription = "home_character_image_${character.id}",
                        url = character.imageUrl,
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        modifier = Modifier.padding(Spacing16),
                        text = character.name
                    )
                }
            }

        is HomeCharacterListViewStateUiModel.Empty -> {
            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                HomeCharacterListContentEmpty()
            }

        }

        is HomeCharacterListViewStateUiModel.Error -> {
            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                HomeCharacterListContentError()
            }
        }
    }
}

@Composable
fun HomeCharacterListContentEmpty() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing16),
        text = stringResource(translations.home_character_list_empty),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun HomeCharacterListContentError() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing16),
        text = stringResource(translations.home_character_list_error),
        textAlign = TextAlign.Center,
    )
}

@Composable
@Preview
fun HomeContentPreviewEmpty() {
    HomeContent(
        viewState = HomeViewStateUiModel(
            isRefreshing = false,
            onRefreshTriggered = {},
            topBarActionData = HomeTopBarActionDataUiModel(
                iconResId = drawables.ic_help_outline_24,
                type = HomeTopBarActionTypeUiModel.Help,
                onClick = {}),
            onboardingViewState = HomeOnboardingViewStateUiModel.Hidden,
            characterListViewState = HomeCharacterListViewStateUiModel.Empty,
            episodeListViewState = HomeEpisodeListViewStateUiModel.Empty,
        ),
        hazeState = remember { HazeState() },
    )
}

@Composable
@Preview
fun HomeContentPreviewError() {
    HomeContent(
        viewState = HomeViewStateUiModel(
            isRefreshing = false,
            onRefreshTriggered = {},
            topBarActionData = HomeTopBarActionDataUiModel(
                iconResId = drawables.ic_help_outline_24,
                type = HomeTopBarActionTypeUiModel.Help,
                onClick = {}),
            onboardingViewState = HomeOnboardingViewStateUiModel.Hidden,
            characterListViewState = HomeCharacterListViewStateUiModel.Error,
            episodeListViewState = HomeEpisodeListViewStateUiModel.Error,
        ),
        hazeState = remember { HazeState() },
    )
}

@Composable
@Preview
fun HomeContentPreviewFilled() {
    HomeContent(
        viewState = HomeViewStateUiModel(
            isRefreshing = false,
            onRefreshTriggered = {},
            topBarActionData = HomeTopBarActionDataUiModel(
                iconResId = drawables.ic_help_outline_24,
                type = HomeTopBarActionTypeUiModel.Help,
                onClick = {}),
            onboardingViewState = HomeOnboardingViewStateUiModel.Hidden,
            characterListViewState = HomeCharacterListViewStateUiModel.Filled(
                characters = persistentListOf(
                    CharacterUiModel(
                        id = 1,
                        name = "Rick",
                        imageUrl = "",
                        onClick = {}
                    ),
                    CharacterUiModel(
                        id = 2,
                        name = "Morty",
                        imageUrl = "",
                        onClick = {}
                    ),
                ),
            ),
            episodeListViewState = HomeEpisodeListViewStateUiModel.Filled(
                episodes = persistentListOf(
                    EpisodeUiModel(
                        id = 1,
                        name = "S01E01",
                        onClick = {}
                    ),
                    EpisodeUiModel(
                        id = 2,
                        name = "S01E02",
                        onClick = {}
                    ),
                ),
            )
        ),
        hazeState = remember { HazeState() },
    )
}

@Composable
@Preview
fun HomeOnboardingPreview() {
    HomeContent(
        viewState = HomeViewStateUiModel(
            isRefreshing = false,
            onRefreshTriggered = {},
            topBarActionData = HomeTopBarActionDataUiModel(
                iconResId = drawables.ic_close_24,
                type = HomeTopBarActionTypeUiModel.Close(true),
                onClick = {}),
            onboardingViewState = HomeOnboardingViewStateUiModel.Visible(overlayColor = Color.Transparent),
            characterListViewState = HomeCharacterListViewStateUiModel.Empty,
            episodeListViewState = HomeEpisodeListViewStateUiModel.Empty,
        ),
        hazeState = remember { HazeState() },
    )
}
