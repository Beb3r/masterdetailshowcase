package com.gromo.masterdetailshowcase.features.characters.domain.data_sources

import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel

interface CharactersRemoteDataSource {

    suspend fun fetchAllCharacters(): Result<List<CharacterDomainModel>>
}
