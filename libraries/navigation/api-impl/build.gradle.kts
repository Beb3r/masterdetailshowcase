import com.gromo.masterdetailshowcase.build_logic.implementation

plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.libraries.navigation.api_impl"
}

dependencies{
    implementation(libs.androidx.navigation)

    implementation(projects.libraries.common)
    implementation(projects.libraries.navigation.api)
    implementation(projects.features.characterDetails.navigation)
    implementation(projects.features.episodeDetails.navigation)
    implementation(projects.features.home.navigation)
}