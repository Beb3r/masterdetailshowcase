package com.gromo.masterdetailshowcase.features.character_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.gromo.masterdetailshowcase.core.characters.domain.use_cases.ObserveCharacterByIdUseCase
import com.gromo.masterdetailshowcase.core.common.dispatchers.AppCoroutineDispatchers
import com.gromo.masterdetailshowcase.core.common.stateIn
import com.gromo.masterdetailshowcase.features.character_details.navigation.CharacterDetailsNavigation
import com.gromo.masterdetailshowcase.features.character_details.navigation.CharacterDetailsScreenRoute
import com.gromo.masterdetailshowcase.features.character_details.presentation.models.CharacterDetailsViewStateUiModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import org.koin.android.annotation.KoinViewModel
import timber.log.Timber

@KoinViewModel
class CharacterDetailsViewModel(
    handle: SavedStateHandle,
    coroutineDispatcher: AppCoroutineDispatchers,
    private val observeCharacterByIdUseCase: ObserveCharacterByIdUseCase,
    private val navigation: CharacterDetailsNavigation,
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val viewState: StateFlow<CharacterDetailsViewStateUiModel> =
        flowOf(handle.toRoute<CharacterDetailsScreenRoute>().id).flatMapLatest {
            observeCharacterByIdUseCase(it)
        }.mapLatest { character ->
            if (character == null) {
                // display error message and navigate back
                CharacterDetailsViewStateUiModel.DEFAULT
            } else {
                CharacterDetailsViewStateUiModel(
                    id = character.id,
                    name = character.name,
                    status = character.status,
                    species = character.species,
                    type = character.type,
                    gender = character.gender,
                    imageUrl = character.imageUrl,
                    episodes = character.episodes.toPersistentList(),

                    )
            }
        }.flowOn(coroutineDispatcher.main)
            .stateIn(
                scope = viewModelScope,
                initialValue = CharacterDetailsViewStateUiModel.DEFAULT
            )

    fun onBackClicked() {
        navigation.navigateBack()
    }
}
