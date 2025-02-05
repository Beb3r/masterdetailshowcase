plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.home.domain"
}

dependencies {
    implementation(projects.core.characters.domain)
    implementation(projects.core.common)
    implementation(projects.core.episodes.domain)
}
