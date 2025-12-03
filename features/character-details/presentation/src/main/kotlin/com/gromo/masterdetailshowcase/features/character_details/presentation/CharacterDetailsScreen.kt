package com.gromo.masterdetailshowcase.features.character_details.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gromo.masterdetailshowcase.features.character_details.presentation.models.CharacterDetailsBodyUiModel
import com.gromo.masterdetailshowcase.features.character_details.presentation.models.CharacterDetailsNavigationIconViewStateUiModel
import com.gromo.masterdetailshowcase.features.character_details.presentation.models.CharacterDetailsViewStateUiModel
import com.gromo.masterdetailshowcase.features.character_details.presentation.models.isVisible
import com.gromo.masterdetailshowcase.libraries.design.AppImage
import com.gromo.masterdetailshowcase.libraries.design.Spacing16
import com.gromo.masterdetailshowcase.libraries.design.Spacing24
import com.gromo.masterdetailshowcase.libraries.design.Spacing8
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import org.koin.androidx.compose.koinViewModel
import kotlin.contracts.ExperimentalContracts
import com.gromo.masterdetailshowcase.libraries.design.R.drawable as drawables
import com.gromo.masterdetailshowcase.libraries.translations.R.string as translations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    viewModel: CharacterDetailsViewModel = koinViewModel(),
    characterId: Int,
    withinScene: Boolean,
) {
    LaunchedEffect(characterId, withinScene) {
        viewModel.initData(characterId = characterId, withinScene = withinScene)
    }

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    CharacterDetailsContent(
        viewState = viewState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsContent(
    viewState: CharacterDetailsViewStateUiModel,
) {
    val hazeState = remember { HazeState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.hazeEffect(
                    state = hazeState,
                    style = HazeStyle(
                        backgroundColor = MaterialTheme.colorScheme.background,
                        tints = emptyList(),
                        blurRadius = 20.dp,
                    )
                ),
                title = {
                    Text(
                        text = viewState.body.name,
                    )
                },
                navigationIcon = {
                    CharacterDetailsNavigationIcon(viewState = viewState.navigationIconViewState)

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = viewState.topBarColor,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black,
                )
            )
        }
    ) { innerPadding ->
        CharacterDetailsBody(
            body = viewState.body,
            innerPadding = innerPadding,
            hazeState = hazeState
        )
    }


}

@OptIn(ExperimentalContracts::class)
@Composable
fun CharacterDetailsNavigationIcon(
    viewState: CharacterDetailsNavigationIconViewStateUiModel,
) {
    if (viewState.isVisible()) {
        IconButton(onClick = viewState.onBackClicked) {
            Icon(
                painter = painterResource(drawables.ic_arrow_back_24),
                contentDescription = ""
            )
        }
    }
}

@OptIn(ExperimentalContracts::class)
@Composable
fun CharacterDetailsBody(
    body: CharacterDetailsBodyUiModel,
    innerPadding: PaddingValues,
    hazeState: HazeState,
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = innerPadding.calculateBottomPadding())
            .hazeSource(state = hazeState)

    ) {
        item {
            AppImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentDescription = "character_details_image_${body.id}",
                url = body.imageUrl,
                placeholder = ColorPainter(Color.LightGray),
            )
        }
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing24, start = Spacing24, end = Spacing24),
                text = stringResource(translations.character_details_name, body.name),
                fontSize = 24.sp
            )
        }
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing16, start = Spacing24, end = Spacing24),
                text = stringResource(translations.character_details_status, body.status),
            )
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing8, start = Spacing24, end = Spacing24),
                text = stringResource(translations.character_details_type, body.type),
            )
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing8, start = Spacing24, end = Spacing24),
                text = stringResource(translations.character_details_gender, body.gender),
            )
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing8, start = Spacing24, end = Spacing24),
                text = stringResource(translations.character_details_appearances),
            )
        }
        items(items = body.episodes, key = { it }) { episode ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Spacing24, end = Spacing24),
                text = episode,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
            )
        }
    }
}
