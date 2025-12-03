package com.gromo.masterdetailshowcase

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.annotation.KoinApplication
import org.koin.ksp.generated.startKoin
import timber.log.Timber

@KoinApplication
class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)

            // TODO plant debug tree only in debug builds
            Timber.plant(Timber.DebugTree())
        }
    }
}
