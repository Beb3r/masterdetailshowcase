package com.gromo.masterdetailshowcase.core.characters.domain.use_cases

import com.gromo.masterdetailshowcase.core.characters.domain.repositories.CharactersRepository
import org.koin.core.annotation.Factory

@Factory
class ObserveCharacterByIdUseCase(private val repository: CharactersRepository) {

    operator fun invoke(id: Int) = repository.observeCharacterById(id = id)
}
