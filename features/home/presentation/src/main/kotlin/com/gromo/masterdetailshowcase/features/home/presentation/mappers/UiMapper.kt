package com.gromo.masterdetailshowcase.features.home.presentation.mappers

import com.gromo.masterdetailshowcase.core.characters.domain.models.CharacterDomainModel
import com.gromo.masterdetailshowcase.features.home.presentation.models.CharacterUiModel

fun CharacterDomainModel.toUiModel(
    onClick: (Int) -> Unit,
) = CharacterUiModel(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl,
    onClick = onClick,
)
