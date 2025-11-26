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

    androidResources {
        localeFilters += listOf("en", "fr")
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        maybeCreate("debug")
        maybeCreate("release")
        named("release") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)


    implementation(projects.libraries.common)
    implementation(projects.libraries.design)
    implementation(projects.libraries.navigation.api)
    implementation(projects.libraries.navigation.apiImpl)
    implementation(projects.libraries.network.api)
    implementation(projects.libraries.network.apiImpl)
    implementation(projects.libraries.persistence.apiImpl)
    implementation(projects.libraries.translations)
    implementation(projects.features.characters.data)
    implementation(projects.features.characters.domain)
    implementation(projects.features.characterDetails.navigation)
    implementation(projects.features.characterDetails.presentation)
    implementation(projects.features.episodes.data)
    implementation(projects.features.episodes.domain)
    implementation(projects.features.episodeDetails.navigation)
    implementation(projects.features.episodeDetails.presentation)
    implementation(projects.features.home.navigation)
    implementation(projects.features.home.domain)
    implementation(projects.features.home.presentation)
    implementation(projects.features.session.data)
    implementation(projects.features.session.domain)
}
