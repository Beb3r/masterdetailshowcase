package com.gromo.masterdetailshowcase.core.common.di

import com.gromo.masterdetailshowcase.core.common.dispatchers.AppCoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val commonModule = module {
    single {
        AppCoroutineDispatchers(
            io = Dispatchers.IO,
            computation = Dispatchers.Default,
            main = Dispatchers.Main,
        )
    }

    val supervisorJob = SupervisorJob()
    factory { CoroutineScope(Dispatchers.IO + supervisorJob) }
}
