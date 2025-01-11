package com.gromo.masterdetailshowcase

import android.app.Application
import com.gromo.masterdetailshowcase.core.common.di.commonModule
import com.gromo.masterdetailshowcase.core.countries.data.di.CountriesDataModule
import com.gromo.masterdetailshowcase.core.countries.domain.di.CountriesDomainModule
import com.gromo.masterdetailshowcase.core.network.api_impl.networkModule
import com.gromo.masterdetailshowcase.core.persistence.api_impl.di.databaseModule
import com.gromo.masterdetailshowcase.features.home.presentation.di.HomePresentationModule
import com.gromo.masterdetailshowcase.features.movie_details.presentation.di.MovieDetailsPresentationModule
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
                CountriesDataModule().module,
                CountriesDomainModule().module,
                HomePresentationModule().module,
                MovieDetailsPresentationModule().module,
            )
        }
    }
}
