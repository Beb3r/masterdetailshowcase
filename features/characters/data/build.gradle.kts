plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.characters.data"
}

dependencies{
    implementation(libs.bundles.ktor)

    implementation(projects.libraries.common)
    implementation(projects.libraries.network.api)
    implementation(projects.libraries.persistence.api)
    implementation(projects.features.characters.domain)
}
