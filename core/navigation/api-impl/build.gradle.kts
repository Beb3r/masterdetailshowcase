import com.gromo.masterdetailshowcase.build_logic.implementation

plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.core.navigation.api_impl"
}

dependencies{
    implementation(libs.androidx.navigation)

    implementation(projects.core.common)
    implementation(projects.core.navigation.api)
    implementation(projects.features.characterDetails.navigation)
    implementation(projects.features.episodeDetails.navigation)
    implementation(projects.features.home.navigation)
}