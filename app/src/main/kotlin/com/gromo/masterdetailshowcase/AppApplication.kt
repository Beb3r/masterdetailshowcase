package com.gromo.masterdetailshowcase

import android.app.Application
import com.gromo.masterdetailshowcase.core.common.di.commonModule
import com.gromo.masterdetailshowcase.core.characters.data.di.CharactersDataModule
import com.gromo.masterdetailshowcase.core.characters.domain.di.CharactersDomainModule
import com.gromo.masterdetailshowcase.core.network.api_impl.networkModule
import com.gromo.masterdetailshowcase.core.persistence.api_impl.di.databaseModule
import com.gromo.masterdetailshowcase.features.home.presentation.di.HomePresentationModule
import com.gromo.masterdetailshowcase.features.character_details.presentation.di.CharacterDetailsPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)

            modules(
                commonModule,
                databaseModule,
                networkModule,
                CharactersDataModule().module,
                CharactersDomainModule().module,
                HomePresentationModule().module,
                CharacterDetailsPresentationModule().module,
            )
        }
    }
}
