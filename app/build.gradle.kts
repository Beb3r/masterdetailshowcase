import com.gromo.masterdetailshowcase.build_logic.implementation

plugins {
    alias(libs.plugins.module.android.application)
    alias(libs.plugins.module.android.presentation)
}

android {
    namespace = "com.gromo.masterdetailshowcase.app"

    defaultConfig {
        applicationId = "com.gromo.masterdetailshowcase"

    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.navigation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // modules
    implementation(projects.core.common)
    implementation(projects.core.navigation.api)
    implementation(projects.core.navigation.apiImpl)
    implementation(projects.core.network.api)
    implementation(projects.core.network.apiImpl)
    implementation(projects.core.persistence.apiImpl)
    implementation(projects.core.characters.data)
    implementation(projects.core.characters.domain)
    implementation(projects.features.characterDetails.navigation)
    implementation(projects.features.characterDetails.presentation)
    implementation(projects.features.home.navigation)
    implementation(projects.features.home.presentation)
}
