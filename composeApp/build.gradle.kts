import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.ksp)
}

kotlin {

    androidTarget {

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->

        iosTarget.binaries.framework {

            baseName = "ComposeApp"
            isStatic = true
        }
    }

    js {

        browser()

        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {

        browser()

        binaries.executable()
    }

    sourceSets {

        androidMain.dependencies {

            implementation(libs.androidx.activity.compose)
            implementation(libs.compose.uiToolingPreview)
        }

        commonMain.dependencies {

            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)

            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)

            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(
                "me.tatarka.inject:kotlin-inject-runtime:0.9.0"
            )
        }

        commonTest.dependencies {

            implementation(libs.kotlin.test)
        }
    }
}

android {

    namespace = "edu.itvo.kmp1"

    compileSdk =
        libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "edu.itvo.kmp1"
        minSdk =
            libs.versions.android.minSdk.get().toInt()
    }

    packaging {

        resources {

            excludes +=
                "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {

        getByName("release") {

            isMinifyEnabled = false
        }
    }

    compileOptions {

        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    debugImplementation(
        libs.compose.uiTooling
    )

    add(
        "kspCommonMainMetadata",
        "me.tatarka.inject:kotlin-inject-compiler-ksp:0.9.0"
    )

    add(
        "kspAndroid",
        "me.tatarka.inject:kotlin-inject-compiler-ksp:0.9.0"
    )

}