plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.module.android.presentation)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.character_details.presentation"
}

dependencies {
    implementation(libs.haze)

    implementation(projects.core.common)
    implementation(projects.core.design)
    implementation(projects.core.characters.domain)
    implementation(projects.core.navigation.api)
    implementation(projects.core.translations)
    implementation(projects.features.characterDetails.navigation)
}
