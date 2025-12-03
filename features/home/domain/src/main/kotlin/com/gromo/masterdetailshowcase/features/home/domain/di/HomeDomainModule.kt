package com.gromo.masterdetailshowcase.features.home.domain.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module

@Module
@Configuration
@ComponentScan("com.gromo.masterdetailshowcase.features.home.domain.use_cases")
class HomeDomainModule
