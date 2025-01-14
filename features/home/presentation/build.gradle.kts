plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.module.android.presentation)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.home.presentation"
}

ksp {
    arg("KOIN_USE_COMPOSE_VIEWMODEL","true")
}

dependencies {
    implementation(libs.haze)

    implementation(projects.core.characters.domain)
    implementation(projects.core.common)
    implementation(projects.core.design)
    implementation(projects.core.session.domain)
    implementation(projects.features.home.navigation)
}
