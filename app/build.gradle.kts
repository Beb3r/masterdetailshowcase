plugins {
    alias(libs.plugins.module.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.gromo.masterdetailshowcase.app"

    defaultConfig {
        applicationId = "com.gromo.masterdetailshowcase"

    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    //implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // modules
    implementation(projects.core.network.api)
    implementation(projects.core.network.apiImpl)
}