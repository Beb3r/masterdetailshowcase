package com.gromo.masterdetailshowcase.core.characters.data.data_sources

import com.gromo.masterdetailshowcase.core.characters.data.mappers.toDomainModel
import com.gromo.masterdetailshowcase.core.characters.data.mappers.toEntityModel
import com.gromo.masterdetailshowcase.core.characters.domain.data_sources.CharactersLocalDataSource
import com.gromo.masterdetailshowcase.core.characters.domain.models.CharacterDomainModel
import com.gromo.masterdetailshowcase.core.persistence.api.daos.CharacterDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single(binds = [CharactersLocalDataSource::class])
class CharactersLocalDataSourceImpl(
    private val characterDao: CharacterDao,
) : CharactersLocalDataSource {

    override fun observeAllCharacters(): Flow<List<CharacterDomainModel>> =
        characterDao.observeAll().map { it.map { country -> country.toDomainModel() } }

    override fun observeCharacterById(id: Int): Flow<CharacterDomainModel?> =
        characterDao.observeById(id = id).map { it?.toDomainModel() }

    override suspend fun saveCharacters(characters: List<CharacterDomainModel>) =
        characterDao.saveCharacters(characters = characters.map { it.toEntityModel() })
}
