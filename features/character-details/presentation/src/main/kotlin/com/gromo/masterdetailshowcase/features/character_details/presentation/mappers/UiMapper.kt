package com.gromo.masterdetailshowcase.features.character_details.presentation.mappers

import android.os.Build
import androidx.compose.ui.graphics.Color
import com.gromo.masterdetailshowcase.features.character_details.presentation.models.CharacterDetailsBodyUiModel
import com.gromo.masterdetailshowcase.features.character_details.presentation.models.CharacterDetailsNavigationIconViewStateUiModel
import com.gromo.masterdetailshowcase.features.character_details.presentation.models.CharacterDetailsViewStateUiModel
import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel
import kotlinx.collections.immutable.toPersistentList

fun toCharacterDetailsViewState(
    character: CharacterDomainModel?,
    withinScene: Boolean,
    onBackClicked: () -> Unit,
): CharacterDetailsViewStateUiModel {
    val topBarColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        Color.Transparent
    } else {
        Color.White.copy(alpha = 0.7f)
    }

    val navigationIconViewState = if (withinScene) {
        CharacterDetailsNavigationIconViewStateUiModel.Hidden
    } else {
        CharacterDetailsNavigationIconViewStateUiModel.Visible(
            onBackClicked = onBackClicked,
        )
    }

    return if (character == null) {
        // TODO: display error state
        CharacterDetailsViewStateUiModel.DEFAULT
    } else {
        val body = CharacterDetailsBodyUiModel(
            id = character.id,
            name = character.name,
            status = character.status,
            species = character.species,
            type = character.type,
            gender = character.gender,
            imageUrl = character.imageUrl,
            episodes = character.episodes.toPersistentList(),
        )
        CharacterDetailsViewStateUiModel(
            title = character.name,
            topBarColor = topBarColor,
            navigationIconViewState = navigationIconViewState,
            body = body,
        )
    }
}
