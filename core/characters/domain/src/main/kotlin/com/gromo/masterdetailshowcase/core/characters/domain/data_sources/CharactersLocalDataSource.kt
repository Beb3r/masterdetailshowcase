package com.gromo.masterdetailshowcase.core.characters.domain.data_sources

import com.gromo.masterdetailshowcase.core.characters.domain.models.CharacterDomainModel
import kotlinx.coroutines.flow.Flow

interface CharactersLocalDataSource {

    fun observeAllCharacters(): Flow<List<CharacterDomainModel>>

    fun observeCharacterById(id: Int): Flow<CharacterDomainModel?>

    suspend fun saveCharacters(characters: List<CharacterDomainModel>)
}
