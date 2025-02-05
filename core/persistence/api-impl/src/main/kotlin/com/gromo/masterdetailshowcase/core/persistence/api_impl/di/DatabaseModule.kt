package com.gromo.masterdetailshowcase.core.persistence.api_impl.di

import androidx.room.Room
import com.gromo.masterdetailshowcase.core.persistence.api_impl.PersistentDataBase
import com.gromo.masterdetailshowcase.core.persistence.api_impl.migrations.Migration1to2
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room
            .databaseBuilder(
                androidContext(),
                PersistentDataBase::class.java,
                PersistentDataBase.DB_NAME
            )
            .fallbackToDestructiveMigrationOnDowngrade()
            .addMigrations(
                Migration1to2(),
            )
            .build()
    }
    single { get<PersistentDataBase>().characterDao() }
    single { get<PersistentDataBase>().episodeDao() }
}
