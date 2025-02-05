package com.gromo.masterdetailshowcase.features.episode_details.navigation

import com.gromo.masterdetailshowcase.core.navigation.api.NavigationRoute
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDetailsScreenRoute(val id: Int) : NavigationRoute