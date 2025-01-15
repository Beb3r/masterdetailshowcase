plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.home.navigation"
}

dependencies {
    implementation(libs.kotlinx.serialization)

    implementation(projects.core.navigation.api)
}