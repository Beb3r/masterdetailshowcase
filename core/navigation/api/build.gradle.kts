import com.gromo.masterdetailshowcase.build_logic.implementation

plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.core.navigation.api"
}

dependencies {
    implementation(libs.androidx.navigation)
}