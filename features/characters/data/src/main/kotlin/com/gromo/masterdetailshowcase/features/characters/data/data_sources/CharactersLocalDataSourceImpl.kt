package com.gromo.masterdetailshowcase.features.characters.data.data_sources

import com.gromo.masterdetailshowcase.features.characters.data.mappers.toDomainModel
import com.gromo.masterdetailshowcase.features.characters.data.mappers.toEntityModel
import com.gromo.masterdetailshowcase.features.characters.domain.data_sources.CharactersLocalDataSource
import com.gromo.masterdetailshowcase.features.characters.domain.models.CharacterDomainModel
import com.gromo.masterdetailshowcase.libraries.persistence.api.daos.CharacterDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single(binds = [CharactersLocalDataSource::class])
class CharactersLocalDataSourceImpl(
    private val dao: CharacterDao,
) : CharactersLocalDataSource {

    override fun observeAllCharacters(): Flow<List<CharacterDomainModel>> =
        dao.observeAll().map { it.map { character -> character.toDomainModel() } }

    override fun observeCharacterById(id: Int): Flow<CharacterDomainModel?> =
        dao.observeById(id = id).map { it?.toDomainModel() }.distinctUntilChanged()

    override suspend fun saveCharacters(characters: List<CharacterDomainModel>) =
        dao.saveCharacters(characters = characters.map { it.toEntityModel() })
}
