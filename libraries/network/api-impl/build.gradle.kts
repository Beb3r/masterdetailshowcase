plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.gromo.masterdetailshowcase.libraries.network.api_impl"
}

dependencies {
    implementation(libs.kotlinx.serialization)
    implementation(libs.bundles.ktor)

    implementation(projects.libraries.network.api)
}
