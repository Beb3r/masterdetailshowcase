plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.character_details.navigation"
}

dependencies {
    implementation(projects.libraries.navigation.api)
}