package com.gromo.masterdetailshowcase.core.characters.domain.data_sources

import com.gromo.masterdetailshowcase.core.characters.domain.models.CharacterDomainModel

interface CharactersRemoteDataSource {

    suspend fun fetchAllCharacters(): Result<List<CharacterDomainModel>>
}
