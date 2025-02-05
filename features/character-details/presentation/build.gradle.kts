plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.module.android.presentation)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.character_details.presentation"
}

dependencies {
    implementation(libs.haze)

    implementation(projects.libraries.common)
    implementation(projects.libraries.design)
    implementation(projects.libraries.navigation.api)
    implementation(projects.libraries.translations)
    implementation(projects.features.characters.domain)
    implementation(projects.features.characterDetails.navigation)
}
