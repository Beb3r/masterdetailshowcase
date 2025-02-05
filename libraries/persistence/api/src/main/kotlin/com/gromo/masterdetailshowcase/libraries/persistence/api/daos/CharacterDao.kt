package com.gromo.masterdetailshowcase.libraries.persistence.api.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gromo.masterdetailshowcase.libraries.persistence.api.entities.CharacterEntityModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Upsert
    suspend fun saveCharacters(characters: List<CharacterEntityModel>)

    @Query("SELECT * FROM character")
    fun observeAll(): Flow<List<CharacterEntityModel>>

    @Query("SELECT * FROM character WHERE id = :id")
    fun observeById(id: Int): Flow<CharacterEntityModel?>
}
