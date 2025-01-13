package com.gromo.masterdetailshowcase.core.characters.domain.use_cases

import com.gromo.masterdetailshowcase.core.characters.domain.repositories.CharactersRepository
import org.koin.core.annotation.Factory

@Factory
class FetchAllCharactersUseCase(private val repository: CharactersRepository) {

    suspend operator fun invoke() = repository.fetchAllCharacters()
}
