package com.gromo.masterdetailshowcase.core.persistence.api.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gromo.masterdetailshowcase.core.persistence.api.entities.CountryEntityModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {
    @Upsert
    suspend fun saveCountries(countries: List<CountryEntityModel>)

    @Query("SELECT * FROM country")
    fun observeAll(): Flow<List<CountryEntityModel>>

    @Query("SELECT * FROM country WHERE id = :id")
    fun observeById(id: String): Flow<CountryEntityModel?>
}
