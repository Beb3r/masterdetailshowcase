package com.gromo.masterdetailshowcase.build_logic

import com.android.build.gradle.BaseExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderConvertible
import org.gradle.kotlin.dsl.accessors.runtime.extensionOf
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.gradle.plugin.use.PluginDependency
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val Project.libs
    get(): LibrariesForLibs = extensionOf(this, "libs") as LibrariesForLibs

fun PluginManager.alias(notation: Provider<PluginDependency>) {
    apply(notation.get().pluginId)
}

fun PluginManager.alias(notation: ProviderConvertible<PluginDependency>) {
    apply(notation.asProvider().get().pluginId)
}

fun DependencyHandler.implementation(provider: Provider<MinimalExternalModuleDependency>) {
    add("implementation", provider.get().group + ":" + provider.get().name + ":" + provider.get().version)
}

fun DependencyHandler.ksp(provider: Provider<MinimalExternalModuleDependency>) {
    add("ksp", provider.get().group + ":" + provider.get().name + ":" + provider.get().version)
}

fun Project.setupAndroidModule(isApplication: Boolean) {
    with(pluginManager) {
        if (isApplication) {
            alias(libs.plugins.android.application)
        } else {
            alias(libs.plugins.android.library)
        }
        alias(libs.plugins.kotlin.android)
        alias(libs.plugins.ksp)
    }

    extensions.configure<BaseExtension> {
        compileSdkVersion(35)

        defaultConfig {
            minSdk = 26
            targetSdk = 35
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        buildTypes {
            buildTypes {
                maybeCreate("debug")
                maybeCreate("release")
                named("release") {
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }

        configureKotlin()
    }
}

private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
            allWarningsAsErrors = true
            freeCompilerArgs += listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview"
            )
        }
    }
}

fun Project.setupBaseDependencies() {
    dependencies {
        implementation(libs.androidx.core.ktx)

        // Coroutines
        implementation(libs.coroutines.asProvider())
        implementation(libs.coroutines.android)

        // Koin
        implementation(libs.koin.asProvider())
        implementation(libs.koin.annotations)
        ksp(libs.koin.ksp.compiler)

        // Timber
        implementation(libs.timber)
    }
}
