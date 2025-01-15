plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.gromo.masterdetailshowcase.core.network.api"
}

dependencies {
    implementation(libs.bundles.ktor)
    implementation(libs.kotlinx.serialization)
}
