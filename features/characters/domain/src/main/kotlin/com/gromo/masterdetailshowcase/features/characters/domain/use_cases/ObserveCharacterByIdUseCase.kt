package com.gromo.masterdetailshowcase.features.characters.domain.use_cases

import com.gromo.masterdetailshowcase.features.characters.domain.repositories.CharactersRepository
import org.koin.core.annotation.Factory

@Factory
class ObserveCharacterByIdUseCase(private val repository: CharactersRepository) {

    operator fun invoke(id: Int) = repository.observeCharacterById(id = id)
}
