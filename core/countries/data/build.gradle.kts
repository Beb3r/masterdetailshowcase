plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.core.countries.data"
}

dependencies{
    implementation(projects.core.countries.domain)
    implementation(projects.core.network.api)
    implementation(projects.core.persistence.api)
    implementation(libs.bundles.ktor)
}
