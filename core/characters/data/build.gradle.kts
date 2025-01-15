plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.core.characters.data"
}

dependencies{
    implementation(libs.bundles.ktor)

    implementation(projects.core.common)
    implementation(projects.core.characters.domain)
    implementation(projects.core.network.api)
    implementation(projects.core.persistence.api)
}
