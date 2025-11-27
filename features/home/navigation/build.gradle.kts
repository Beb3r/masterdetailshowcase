plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.home.navigation"
}

dependencies {
    implementation(projects.libraries.navigation.api)
}