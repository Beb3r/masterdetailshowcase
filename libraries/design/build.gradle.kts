plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.module.android.presentation)
}

android {
    namespace = "com.gromo.masterdetailshowcase.libraries.design"
}

dependencies {
    implementation(libs.bundles.coil)
}
