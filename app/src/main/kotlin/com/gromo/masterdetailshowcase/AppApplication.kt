package com.gromo.masterdetailshowcase

import android.app.Application
import com.gromo.masterdetailshowcase.features.characters.data.di.CharactersDataModule
import com.gromo.masterdetailshowcase.features.characters.domain.di.CharactersDomainModule
import com.gromo.masterdetailshowcase.libraries.common.di.CommonModule
import com.gromo.masterdetailshowcase.features.episodes.data.di.EpisodesDataModule
import com.gromo.masterdetailshowcase.features.episodes.domain.di.EpisodesDomainModule
import com.gromo.masterdetailshowcase.libraries.navigation.api_impl.di.NavigationModule
import com.gromo.masterdetailshowcase.libraries.network.api_impl.NetworkModule
import com.gromo.masterdetailshowcase.libraries.persistence.api_impl.di.DatabaseModule
import com.gromo.masterdetailshowcase.features.session.data.di.SessionDataModule
import com.gromo.masterdetailshowcase.features.session.domain.di.SessionDomainModule
import com.gromo.masterdetailshowcase.features.home.presentation.di.HomePresentationModule
import com.gromo.masterdetailshowcase.features.character_details.presentation.di.CharacterDetailsPresentationModule
import com.gromo.masterdetailshowcase.features.episode_details.presentation.di.EpisodeDetailsPresentationModule
import com.gromo.masterdetailshowcase.features.home.domain.di.HomeDomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module
import timber.log.Timber

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)

            // TODO plant debug tree only in debug builds
            Timber.plant(Timber.DebugTree())

            modules(
                CharactersDataModule().module,
                CharactersDomainModule().module,
                CharacterDetailsPresentationModule().module,
                CommonModule().module,
                DatabaseModule().module,
                EpisodeDetailsPresentationModule().module,
                EpisodesDataModule().module,
                EpisodesDomainModule().module,
                HomeDomainModule().module,
                HomePresentationModule().module,
                NavigationModule().module,
                NetworkModule().module,
                SessionDataModule().module,
                SessionDomainModule().module,
            )
        }
    }
}
