plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.session.data"
}

dependencies {
    implementation(projects.libraries.common)
    implementation(projects.features.session.domain)
}
