package com.gromo.masterdetailshowcase.features.characters.domain.use_cases

import com.gromo.masterdetailshowcase.features.characters.domain.repositories.CharactersRepository
import org.koin.core.annotation.Factory

@Factory
class ObserveAllCharactersUseCase(private val repository: CharactersRepository) {

    operator fun invoke() = repository.observeAllCharacters()
}
