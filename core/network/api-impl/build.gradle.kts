plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.gromo.masterdetailshowcase.core.network.api_impl"
}

dependencies {
    implementation(libs.kotlinx.serialization)
}