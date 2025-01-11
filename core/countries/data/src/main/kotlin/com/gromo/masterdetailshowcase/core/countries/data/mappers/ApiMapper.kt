package com.gromo.masterdetailshowcase.core.countries.data.mappers

import com.gromo.masterdetailshowcase.core.network.api.models.CountryApiModel
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel.Companion.DEFAULT_ALT_SPELLINGS
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel.Companion.DEFAULT_AREA
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel.Companion.DEFAULT_CAPITAL
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel.Companion.DEFAULT_CONTINENTS
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel.Companion.DEFAULT_FLAG_URL
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel.Companion.DEFAULT_INDEPENDENT
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel.Companion.DEFAULT_NAME_COMMON
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel.Companion.DEFAULT_NAME_OFFICIAL
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel.Companion.DEFAULT_POPULATION
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel.Companion.DEFAULT_REGION
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel.Companion.DEFAULT_SUBREGION
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel.Companion.DEFAULT_UN_MEMBER

fun CountryApiModel?.toDomainModel(): CountryDomainModel? =
    this?.id?.let {
        CountryDomainModel(
            id = it,
            nameCommon = this.name?.common ?: DEFAULT_NAME_COMMON,
            nameOfficial = this.name?.official ?: DEFAULT_NAME_OFFICIAL,
            independent = this.independent ?: DEFAULT_INDEPENDENT,
            unMember = this.unMember ?: DEFAULT_UN_MEMBER,
            capital = this.capital ?: DEFAULT_CAPITAL,
            altSpellings = this.altSpellings ?: DEFAULT_ALT_SPELLINGS,
            continents = this.continents ?: DEFAULT_CONTINENTS,
            region = this.region ?: DEFAULT_REGION,
            subregion = this.subregion ?: DEFAULT_SUBREGION,
            flagUrl = this.flags?.pngUrl ?: DEFAULT_FLAG_URL,
            area = this.area ?: DEFAULT_AREA,
            population = this.population ?: DEFAULT_POPULATION,
        )
    }
