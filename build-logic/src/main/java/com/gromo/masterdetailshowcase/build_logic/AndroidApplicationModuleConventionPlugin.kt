package com.gromo.masterdetailshowcase.build_logic

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationModuleConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            setupAndroidModule(isApplication = true)
            setupBaseDependencies()
        }
    }
}