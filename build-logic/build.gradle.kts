import org.gradle.initialization.DependenciesAccessors
import org.gradle.kotlin.dsl.support.serviceOf

plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)

    gradle.serviceOf<DependenciesAccessors>().classes.asFiles.forEach {
        compileOnly(files(it.absolutePath))
    }
}

group = "com.gromo.masterdetailshowcase.build-logic"

gradlePlugin {
    plugins {
        register("moduleAndroidApplication") {
            id = "com.gromo.masterdetailshowcase.module.android.application"
            implementationClass = "com.gromo.masterdetailshowcase.build_logic.AndroidApplicationModuleConventionPlugin"
        }
        register("moduleAndroidLibrary") {
            id = "com.gromo.masterdetailshowcase.module.android.library"
            implementationClass = "com.gromo.masterdetailshowcase.build_logic.AndroidLibraryModuleConventionPlugin"
        }
    }
}
