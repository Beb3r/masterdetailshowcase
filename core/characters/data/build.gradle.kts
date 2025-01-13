plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.core.characters.data"
}

dependencies{
    implementation(projects.core.common)
    implementation(projects.core.characters.domain)
    implementation(projects.core.network.api)
    implementation(projects.core.persistence.api)
    implementation(libs.bundles.ktor)
}
