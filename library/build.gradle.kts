import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.composeMultiplatform)
}

group = "com.impacto.impactoui"
version = "1.0.0"

kotlin {
    // Target JVM (desktop / server)
//    jvm()

    // Target Android
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

//    // Target iOS
//    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // Opsional tambahan (Linux)
//    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.runtime)
                implementation(libs.foundation)
                implementation(libs.material3)
                implementation(libs.kotlinx.datetime)
                implementation(compose.components.resources)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.compose.ui.tooling)
                implementation(libs.compose.ui.tooling.preview)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        // --- Perbaikan Hirarki iOS ---
        // Dapatkan referensi ke source set yang dibuat otomatis oleh target iOS
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        // Buat source set perantara 'iosMain' untuk berbagi kode antar target iOS
        val iosMain by creating {
            // iosMain bergantung pada commonMain
            dependsOn(commonMain)

            // Buat target spesifik (iosArm64, iosSimulatorArm64) bergantung pada iosMain
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    namespace = "com.impacto.impactoui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}
