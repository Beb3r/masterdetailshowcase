package com.gromo.masterdetailshowcase.features.characters.domain.data_sources

import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel
import kotlinx.coroutines.flow.Flow

interface CharactersLocalDataSource {

    fun observeAllCharacters(): Flow<List<CharacterDomainModel>>

    fun observeCharacterById(id: Int): Flow<CharacterDomainModel?>

    suspend fun saveCharacters(characters: List<CharacterDomainModel>)
}
