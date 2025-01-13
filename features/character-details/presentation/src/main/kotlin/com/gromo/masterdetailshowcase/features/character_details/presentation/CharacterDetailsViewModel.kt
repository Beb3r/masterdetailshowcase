package com.gromo.masterdetailshowcase.features.character_details.presentation

import androidx.lifecycle.ViewModel
import com.gromo.masterdetailshowcase.core.characters.domain.use_cases.ObserveCharacterByIdUseCase
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CharacterDetailsViewModel(
    private val observeCharacterByIdUseCase: ObserveCharacterByIdUseCase,
): ViewModel() {

}
