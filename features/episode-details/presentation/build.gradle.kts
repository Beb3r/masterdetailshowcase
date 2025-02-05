plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.module.android.presentation)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.episode_details.presentation"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.design)
    implementation(projects.core.episodes.domain)
    implementation(projects.core.navigation.api)
    implementation(projects.core.translations)
    implementation(projects.features.episodeDetails.navigation)
}
