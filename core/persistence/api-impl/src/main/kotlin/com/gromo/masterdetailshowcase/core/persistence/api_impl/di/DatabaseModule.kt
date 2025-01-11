package com.gromo.masterdetailshowcase.core.persistence.api_impl.di

import androidx.room.Room
import com.gromo.masterdetailshowcase.core.persistence.api_impl.PersistentDataBase
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
            .build()
    }
    single { get<PersistentDataBase>().countryDao() }
}
