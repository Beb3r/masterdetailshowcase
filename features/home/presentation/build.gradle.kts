plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.module.android.presentation)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.home.presentation"
}

dependencies {
    implementation(libs.haze)

    implementation(projects.core.characters.domain)
    implementation(projects.core.common)
    implementation(projects.core.design)
    implementation(projects.core.episodes.domain)
    implementation(projects.core.session.domain)
    implementation(projects.core.translations)
    implementation(projects.features.home.domain)
    implementation(projects.features.home.navigation)
}
