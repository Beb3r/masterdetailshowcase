package com.gromo.masterdetailshowcase.features.character_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gromo.masterdetailshowcase.features.character_details.navigation.CharacterDetailsNavigation
import com.gromo.masterdetailshowcase.features.character_details.presentation.mappers.toCharacterDetailsViewState
import com.gromo.masterdetailshowcase.features.character_details.presentation.models.CharacterDetailsViewStateUiModel
import com.gromo.masterdetailshowcase.features.characters.domain.use_cases.ObserveCharacterByIdUseCase
import com.gromo.masterdetailshowcase.libraries.common.stateIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CharacterDetailsViewModel(
    private val observeCharacterByIdUseCase: ObserveCharacterByIdUseCase,
    private val navigation: CharacterDetailsNavigation,
) : ViewModel() {

    private val initDataFlow = MutableStateFlow<Pair<Int, Boolean>?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val viewState: StateFlow<CharacterDetailsViewStateUiModel> =
        initDataFlow.filterNotNull().flatMapLatest { (characterId, withinScene) ->
            observeCharacterByIdUseCase(characterId).map { character ->
                character to withinScene
            }
        }.mapLatest { (character, withinScene) ->
            toCharacterDetailsViewState(
                character = character,
                withinScene = withinScene,
                onBackClicked = ::onBackClicked
            )
        }.stateIn(
            scope = viewModelScope,
            initialValue = CharacterDetailsViewStateUiModel.DEFAULT
        )

    fun initData(characterId: Int, withinScene: Boolean) {
        initDataFlow.value = characterId to withinScene
    }

    private fun onBackClicked() {
        navigation.navigateBack()
    }
}
