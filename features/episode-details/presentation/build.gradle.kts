plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.module.android.presentation)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.episode_details.presentation"
}

dependencies {
    implementation(libs.androidx.navigation3.runtime)

    implementation(projects.libraries.common)
    implementation(projects.libraries.design)
    implementation(projects.libraries.navigation.api)
    implementation(projects.libraries.translations)
    implementation(projects.features.episodes.domain)
    implementation(projects.features.episodeDetails.navigation)
}
