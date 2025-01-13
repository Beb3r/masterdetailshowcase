package com.gromo.masterdetailshowcase.core.persistence.api_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gromo.masterdetailshowcase.core.persistence.api.daos.CharacterDao
import com.gromo.masterdetailshowcase.core.persistence.api.entities.CharacterEntityModel
import com.gromo.masterdetailshowcase.core.persistence.api_impl.PersistentDataBase.Companion.DB_VERSION
import com.gromo.masterdetailshowcase.core.persistence.api_impl.converters.Converters


@Database(
    entities = [
        CharacterEntityModel::class,
    ],
    exportSchema = true, version = DB_VERSION
)
@TypeConverters(Converters::class)
internal abstract class PersistentDataBase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "app_database"
    }
}
