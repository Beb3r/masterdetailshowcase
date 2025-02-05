import com.gromo.masterdetailshowcase.build_logic.libs

plugins {
    alias(libs.plugins.module.android.library)
}

android {
    namespace = "com.gromo.masterdetailshowcase.libraries.persistence.api"
}

dependencies {
    implementation(libs.room.common)
}
