package com.gromo.masterdetailshowcase.build_logic

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryModuleConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            setupAndroidModule(isApplication = false)
            setupBaseDependencies()
        }
    }
}