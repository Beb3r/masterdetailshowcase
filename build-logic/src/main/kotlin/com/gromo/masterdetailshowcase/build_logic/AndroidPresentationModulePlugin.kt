package com.gromo.masterdetailshowcase.build_logic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidPresentationModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.kotlin.compose)
            }
            dependencies {
                implementation(libs.androidx.navigation)
                implementation(libs.androidx.lifecycle.runtime.ktx)
                implementation(platform(libs.androidx.compose.bom))
                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.compose.ui.toolingx)
                implementation(libs.androidx.compose.ui.tooling.preview)
                implementation(libs.androidx.material3)
                implementation(libs.koin.compose.asProvider())
                implementation(libs.koin.compose.navigation)
                implementation(libs.kotlinx.collections.immutable)
            }
        }
    }
}