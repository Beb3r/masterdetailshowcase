import com.gromo.masterdetailshowcase.build_logic.implementation

plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.core.navigation.api_impl"
}

dependencies{
    implementation(projects.core.common)
    implementation(projects.core.navigation.api)
    implementation(projects.features.characterDetails.navigation)
    implementation(projects.features.home.navigation)
    implementation(libs.androidx.navigation)
}