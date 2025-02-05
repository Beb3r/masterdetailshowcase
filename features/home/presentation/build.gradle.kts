plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.module.android.presentation)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.home.presentation"
}

dependencies {
    implementation(libs.haze)

    implementation(projects.libraries.common)
    implementation(projects.libraries.design)
    implementation(projects.libraries.translations)
    implementation(projects.features.characters.domain)
    implementation(projects.features.episodes.domain)
    implementation(projects.features.home.domain)
    implementation(projects.features.home.navigation)
    implementation(projects.features.session.domain)
}
