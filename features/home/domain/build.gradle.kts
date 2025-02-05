plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.home.domain"
}

dependencies {
    implementation(projects.libraries.common)
    implementation(projects.features.characters.domain)
    implementation(projects.features.episodes.domain)
}
