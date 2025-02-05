package com.gromo.masterdetailshowcase.features.episode_details.presentation

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gromo.masterdetailshowcase.core.design.Spacing16
import com.gromo.masterdetailshowcase.core.design.Spacing24
import com.gromo.masterdetailshowcase.core.design.Spacing8
import com.gromo.masterdetailshowcase.features.episode_details.presentation.models.EpisodeDetailsViewStateUiModel
import org.koin.androidx.compose.koinViewModel
import com.gromo.masterdetailshowcase.core.design.R.drawable as drawables
import com.gromo.masterdetailshowcase.core.translations.R.string as translations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodeDetailsScreen(
    viewModel: EpisodeDetailsViewModel = koinViewModel(),
) {


    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = viewState.episode,
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
            )
        }
    ) { innerPadding ->
        EpisodeDetailsContent(
            viewState = viewState,
            innerPadding = innerPadding,
        )
    }
}

@Composable
fun EpisodeDetailsContent(
    viewState: EpisodeDetailsViewStateUiModel,
    innerPadding: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
    ) {
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
                    text = stringResource(translations.episode_details_name, viewState.name),
                    fontSize = 24.sp
                )
            }
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing16),
                    text = stringResource(translations.episode_details_air_date, viewState.airDate),
                )
            }
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing8),
                    text = stringResource(translations.episode_details_cast),
                )
            }
            items(items = viewState.characters, key = { it }) { episode ->
                Text(
                    text = episode,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                )
            }
        }
    }
}
