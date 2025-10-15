import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.compose)
    alias(libs.plugins.kmp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.compose.viewmodel)
            implementation(libs.compose.navigation)
            implementation(libs.bundles.decompose)
            implementation(libs.kotlin.serialization.json)
            implementation(libs.composeIcons.lineAwesome)
            implementation(libs.composeIcons.simpleIcons)
            implementation(libs.composeIcons.evaIcons)
            implementation(libs.essenty.lifecycle.coroutines)
        }
    }
}