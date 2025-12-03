package com.gromo.masterdetailshowcase.libraries.common.di

import com.gromo.masterdetailshowcase.libraries.common.dispatchers.AppCoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@Configuration
@ComponentScan("com.gromo.masterdetailshowcase.libraries.common")
class CommonModule

@Single
fun createDispatchers(): AppCoroutineDispatchers =
    AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main,
    )

@Factory
fun createAppScope(): CoroutineScope {
    val supervisorJob = SupervisorJob()
    return CoroutineScope(Dispatchers.IO + supervisorJob)
}
