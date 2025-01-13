package com.gromo.masterdetailshowcase.core.characters.domain.repositories

import com.gromo.masterdetailshowcase.core.characters.domain.data_sources.CharactersLocalDataSource
import com.gromo.masterdetailshowcase.core.characters.domain.data_sources.CharactersRemoteDataSource
import com.gromo.masterdetailshowcase.core.characters.domain.models.CharacterDomainModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class CharactersRepository(
    private val remoteDataSource: CharactersRemoteDataSource,
    private val localDataSource: CharactersLocalDataSource,
) {

    suspend fun fetchAllCharacters(): Result<Unit> =
        remoteDataSource.fetchAllCharacters().map {
            localDataSource.saveCharacters(characters = it)
        }

    fun observeAllCharacters(): Flow<List<CharacterDomainModel>> =
        localDataSource.observeAllCharacters()

    fun observeCharacterById(id: Int): Flow<CharacterDomainModel?> =
        localDataSource.observeCharacterById(id = id)
}
