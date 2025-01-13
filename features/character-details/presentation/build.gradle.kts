plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.module.android.presentation)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.character_details.presentation"
}

ksp {
    arg("KOIN_USE_COMPOSE_VIEWMODEL","true")
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.characters.domain)
}
