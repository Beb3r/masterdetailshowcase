plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.core.session.data"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.session.domain)
}
