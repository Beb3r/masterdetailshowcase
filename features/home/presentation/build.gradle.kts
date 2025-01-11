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
    implementation(projects.core.common)
    implementation(projects.core.countries.domain)
}
