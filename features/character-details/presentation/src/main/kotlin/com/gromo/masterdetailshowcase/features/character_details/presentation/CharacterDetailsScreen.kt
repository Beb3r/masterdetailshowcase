package com.gromo.masterdetailshowcase.features.character_details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gromo.masterdetailshowcase.core.design.AppImage
import com.gromo.masterdetailshowcase.core.design.Spacing16
import com.gromo.masterdetailshowcase.core.design.Spacing24
import com.gromo.masterdetailshowcase.core.design.Spacing8
import com.gromo.masterdetailshowcase.features.character_details.presentation.models.CharacterDetailsViewStateUiModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import org.koin.androidx.compose.koinViewModel
import com.gromo.masterdetailshowcase.core.design.R.drawable as drawables
import com.gromo.masterdetailshowcase.core.translations.R.string as translations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    viewModel: CharacterDetailsViewModel = koinViewModel(),
) {

    val hazeState = remember { HazeState() }

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.hazeEffect(
                    state = hazeState,
                    style = HazeStyle(
                        backgroundColor = Color.Black,
                        tints = emptyList(),
                        blurRadius = 20.dp,
                    )
                ),
                title = {
                    Text(
                        text = viewState.name,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = viewModel::onBackClicked) {
                        Icon(
                            painter = painterResource(drawables.ic_arrow_back_24),
                            contentDescription = ""
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = viewState.topBarColor,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black,
                )
            )
        }
    ) { innerPadding ->
        CharacterDetailsContent(
            viewState = viewState,
            innerPadding = innerPadding,
            hazeState = hazeState
        )
    }
}

@Composable
fun CharacterDetailsContent(
    viewState: CharacterDetailsViewStateUiModel,
    innerPadding: PaddingValues,
    hazeState: HazeState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = innerPadding.calculateBottomPadding())
            .hazeSource(state = hazeState)
    ) {
        AppImage(
            modifier = Modifier.fillMaxWidth(),
            contentDescription = "character_details_image_${viewState.id}",
            url = viewState.imageUrl,
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = Spacing24)
                .fillMaxWidth()

        ) {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing24),
                    text = stringResource(translations.character_details_name, viewState.name),
                    fontSize = 24.sp
                )
            }
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing16),
                    text = stringResource(translations.character_details_status, viewState.status),
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing8),
                    text = stringResource(translations.character_details_type, viewState.type),
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing8),
                    text = stringResource(translations.character_details_gender, viewState.gender),
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing8),
                    text = stringResource(translations.character_details_appearances),
                )
            }
            items(items = viewState.episodes, key = { it }) { episode ->
                Text(
                    text = episode,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                )
            }
        }
    }
}
