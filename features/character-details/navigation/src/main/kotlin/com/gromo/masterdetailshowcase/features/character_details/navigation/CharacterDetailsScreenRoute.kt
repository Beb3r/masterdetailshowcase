package com.gromo.masterdetailshowcase.features.character_details.navigation

import com.gromo.masterdetailshowcase.libraries.navigation.api.NavigationRoute
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailsScreenRoute(val id: Int) : NavigationRoute