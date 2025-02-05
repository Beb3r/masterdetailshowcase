import com.gromo.masterdetailshowcase.build_logic.libs

plugins {
    alias(libs.plugins.module.android.library)
    alias(libs.plugins.room)
}

android {
    namespace = "com.gromo.masterdetailshowcase.libraries.persistence.api_impl"
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    implementation(libs.bundles.room)
    ksp(libs.room.ksp)

    implementation(projects.libraries.persistence.api)
}
