package com.gromo.masterdetailshowcase.core.countries.data.mappers

import com.gromo.masterdetailshowcase.core.persistence.api.entities.CountryEntityModel
import com.gromo.masterdetailshowcase.core.countries.domain.models.CountryDomainModel

fun CountryDomainModel.toEntityModel(): CountryEntityModel =
    CountryEntityModel(
        id = this.id,
        nameCommon = this.nameCommon,
        nameOfficial = this.nameOfficial,
        independent = this.independent,
        unMember = this.unMember,
        capital = this.capital,
        altSpellings = this.altSpellings,
        continents = this.continents,
        region = this.region,
        subregion = this.subregion,
        flagUrl = this.flagUrl,
        area = this.area,
        population = this.population,
    )

fun CountryEntityModel.toDomainModel(): CountryDomainModel =
    CountryDomainModel(
        id = this.id,
        nameCommon = this.nameCommon,
        nameOfficial = this.nameOfficial,
        independent = this.independent,
        unMember = this.unMember,
        capital = this.capital,
        altSpellings = this.altSpellings,
        continents = this.continents,
        region = this.region,
        subregion = this.subregion,
        flagUrl = this.flagUrl,
        area = this.area,
        population = this.population,
    )
