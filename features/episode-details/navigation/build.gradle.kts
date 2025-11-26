plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.episode_details.navigation"
}

dependencies {
    implementation(projects.libraries.navigation.api)
}