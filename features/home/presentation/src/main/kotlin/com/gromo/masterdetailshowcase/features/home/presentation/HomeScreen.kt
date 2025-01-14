package com.gromo.masterdetailshowcase.features.home.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gromo.masterdetailshowcase.core.design.AppImage
import com.gromo.masterdetailshowcase.core.design.Spacing16
import com.gromo.masterdetailshowcase.core.design.Spacing24
import com.gromo.masterdetailshowcase.core.design.Spacing48
import com.gromo.masterdetailshowcase.core.design.Spacing8
import com.gromo.masterdetailshowcase.features.home.presentation.models.CharacterUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeCharacterListViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeOnboardingViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeTopBarActionViewStateUiModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeViewStateUiModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.koin.androidx.compose.koinViewModel
import com.gromo.masterdetailshowcase.core.design.R.drawable as drawables

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
                        text = "Rick & Morty",
                    )
                },
                actions = {
                    AnimatedContent(
                        targetState = viewState.topBarActionViewState,
                        transitionSpec = {
                            scaleIn() togetherWith scaleOut()
                        },
                        label = "toolbar action animation"
                    ) { targetState ->
                        when (targetState) {
                            is HomeTopBarActionViewStateUiModel.Help -> {
                                IconButton(onClick = targetState.onClick) {
                                    Icon(
                                        painter = painterResource(drawables.ic_help_outline_24),
                                        contentDescription = ""
                                    )
                                }
                            }

                            is HomeTopBarActionViewStateUiModel.Close -> {
                                IconButton(onClick = targetState.onClick) {
                                    Icon(
                                        painter = painterResource(drawables.ic_close_24),
                                        contentDescription = ""
                                    )
                                }
                            }
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
            HomeContent(viewState = viewState, hazeState = hazeState)
            HomeOnboardingContent(viewState = viewState.onboardingViewState, hazeState = hazeState)
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
            isRefreshing = viewState.isRefreshing,
            onRefresh = viewState.onRefreshTriggered,
            modifier = Modifier.fillMaxWidth(),
        ) {
            when (val characterListViewState = viewState.characterListViewState) {
                is HomeCharacterListViewStateUiModel.Empty -> HomeContentEmpty()
                is HomeCharacterListViewStateUiModel.Error -> HomeContentError()
                is HomeCharacterListViewStateUiModel.Filled -> HomeContentFilled(characters = characterListViewState.characters)
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
        (LocalConfiguration.current.screenWidthDp.toFloat() / 2f) - Spacing24.value

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = Spacing8, end = Spacing8),
        columns = GridCells.Adaptive(requiredCellSize.dp),
        state = gridState,
    ) {
        items(items = characters, key = { it.id }) { character ->
            Card(
                modifier = Modifier
                    .padding(Spacing8)
                    .fillMaxWidth(),
                onClick = {
                    character.onClick(character.id)
                }
            ) {
                AppImage(
                    url = character.imageUrl,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = character.name,
                    modifier = Modifier.padding(Spacing16)
                )
            }
        }
    }
}

@Composable
fun HomeOnboardingContent(viewState: HomeOnboardingViewStateUiModel, hazeState: HazeState) {
    Crossfade(
        targetState = viewState,
        label = "crossfade onboarding animation",
    ) { targetState ->
        if (targetState is HomeOnboardingViewStateUiModel.Visible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            backgroundColor = if (isSystemInDarkTheme()) {
                                Color.Black
                            } else {
                                Color.White
                            },
                            tints = emptyList(),
                            blurRadius = 20.dp
                        )
                    )
                    .clickable {}
            ) {
                Card(
                    modifier = Modifier
                        .padding(Spacing48)
                        .fillMaxWidth()
                        .align(Alignment.Center),
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Spacing24),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(drawables.logo_rick_and_morty),
                            contentDescription = "logo"
                        )

                        Text(
                            modifier = Modifier.padding(top = Spacing16),
                            text = "Welcome to Rick & Morty!",
                            fontSize = TextUnit(value = 20f, TextUnitType.Sp),
                            textAlign = TextAlign.Center,
                        )

                        Text(
                            modifier = Modifier.padding(top = Spacing16),
                            text = "This is a demo app to showcase best practices around clean archicture and modularization. Please don't mind the design, it ain't its purpose",
                            fontSize = TextUnit(value = 16f, TextUnitType.Sp),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeContentEmpty() {
    Text(
        text = "Empty",
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing16)
    )
}

@Composable
fun HomeContentError() {
    Text(
        text = "Error",
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing16)
    )
}

@Composable
@Preview
fun HomeContentPreviewEmpty() {
    HomeContent(
        viewState = HomeViewStateUiModel(
            isRefreshing = false,
            onRefreshTriggered = {},
            topBarActionViewState = HomeTopBarActionViewStateUiModel.Help(
                onClick = {}
            ),
            onboardingViewState = HomeOnboardingViewStateUiModel.Hidden,
            characterListViewState = HomeCharacterListViewStateUiModel.Empty,
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
            topBarActionViewState = HomeTopBarActionViewStateUiModel.Help(
                onClick = {}
            ),
            onboardingViewState = HomeOnboardingViewStateUiModel.Hidden,
            characterListViewState = HomeCharacterListViewStateUiModel.Error,
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
            topBarActionViewState = HomeTopBarActionViewStateUiModel.Help(
                onClick = {}
            ),
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
        ),
        hazeState = remember { HazeState() },
    )
}

@Composable
@Preview
fun HomeOnboardinPreview() {
    HomeContent(
        viewState = HomeViewStateUiModel(
            isRefreshing = false,
            onRefreshTriggered = {},
            topBarActionViewState = HomeTopBarActionViewStateUiModel.Close(
                onClick = {}
            ),
            onboardingViewState = HomeOnboardingViewStateUiModel.Visible,
            characterListViewState = HomeCharacterListViewStateUiModel.Empty,
        ),
        hazeState = remember { HazeState() },
    )
}
