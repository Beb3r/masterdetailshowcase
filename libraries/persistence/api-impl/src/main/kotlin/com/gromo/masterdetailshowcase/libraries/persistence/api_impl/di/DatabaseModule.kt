package com.gromo.masterdetailshowcase.libraries.persistence.api_impl.di

import android.content.Context
import androidx.room.Room
import com.gromo.masterdetailshowcase.libraries.persistence.api_impl.PersistentDataBase
import com.gromo.masterdetailshowcase.libraries.persistence.api_impl.migrations.Migration1to2
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@Configuration
@ComponentScan("com.gromo.masterdetailshowcase.libraries.persistence.api_impl.di")
class DatabaseModule

@Single
internal fun createDatabase(context: Context) =
    Room
        .databaseBuilder(
            context,
            PersistentDataBase::class.java,
            PersistentDataBase.DB_NAME
        )
        .fallbackToDestructiveMigrationOnDowngrade()
        .addMigrations(
            Migration1to2(),
        )
        .build()
@Single
internal fun createCharacterDao(database: PersistentDataBase) = database.characterDao()

@Single
internal fun createEpisodeDao(database: PersistentDataBase) = database.episodeDao()
