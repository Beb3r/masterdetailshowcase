package com.gromo.masterdetailshowcase.libraries.persistence.api.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gromo.masterdetailshowcase.libraries.persistence.api.entities.EpisodeEntityModel
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {

    @Upsert
    suspend fun saveEpisodes(episodes: List<EpisodeEntityModel>)

    @Query("SELECT * FROM episode")
    fun observeAll(): Flow<List<EpisodeEntityModel>>

    @Query("SELECT * FROM episode WHERE id = :id")
    fun observeById(id: Int): Flow<EpisodeEntityModel?>
}
