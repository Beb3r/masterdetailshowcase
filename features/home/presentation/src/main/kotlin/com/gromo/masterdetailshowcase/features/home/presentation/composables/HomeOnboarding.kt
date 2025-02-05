package com.gromo.masterdetailshowcase.features.home.presentation.composables

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.gromo.masterdetailshowcase.core.design.Spacing16
import com.gromo.masterdetailshowcase.core.design.Spacing24
import com.gromo.masterdetailshowcase.core.design.Spacing48
import com.gromo.masterdetailshowcase.features.home.presentation.models.HomeOnboardingViewStateUiModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeEffect
import com.gromo.masterdetailshowcase.core.design.R.drawable as drawables
import com.gromo.masterdetailshowcase.core.translations.R.string as translations

@Composable
fun HomeOnboarding(viewState: HomeOnboardingViewStateUiModel, hazeState: HazeState) {
    Crossfade(
        targetState = viewState,
        label = "crossfade_onboarding_animation",
    ) { targetState ->
        if (targetState is HomeOnboardingViewStateUiModel.Visible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = targetState.overlayColor)
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            backgroundColor =  if (isSystemInDarkTheme()) {
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
                            contentDescription = "onboarding_logo"
                        )

                        Text(
                            modifier = Modifier.padding(top = Spacing16),
                            text = stringResource(translations.home_onboarding_title),
                            fontSize = TextUnit(value = 20f, TextUnitType.Sp),
                            textAlign = TextAlign.Center,
                        )

                        Text(
                            modifier = Modifier.padding(top = Spacing16),
                            text = stringResource(translations.home_onboarding_subtitle),
                            fontSize = TextUnit(value = 16f, TextUnitType.Sp),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}