plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.features.session.data"
}

dependencies {
    implementation(libs.bundles.datastore)
    implementation(projects.libraries.common)
    implementation(projects.features.session.domain)

}
