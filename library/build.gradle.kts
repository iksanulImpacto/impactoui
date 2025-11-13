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
version = "1.0.2"

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

//publishing {
//    repositories {
//        maven {
//            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
//            credentials {
//                username = findProperty("ossrhUsername")?.toString()
//                password = findProperty("ossrhPassword")?.toString()
//            }
//        }
//    }
//}


mavenPublishing {
    coordinates(
        groupId = "io.github.iksanulimpacto",
        artifactId = "impactoui", // Nama pustaka Anda
        version = "1.0.2"
    )

    pom {
        name.set("ImpactoUI")
        description.set("A set of custom UI components for Compose Multiplatform.")
        url.set("https://github.com/iksanulimpacto/impactoui") // URL proyek Anda

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("iksanulimpacto") // username GitHub/id Anda
                name.set("Muhammad Iksanul")
                email.set("iksanul@impacto.id")
            }
        }
        scm {
            url.set("https://github.com/iksanulimpacto/impactoui")
            connection.set("scm:git:github.com/iksanulimpacto/impactoui.git")
            developerConnection.set("scm:git:ssh://github.com/iksanulimpacto/impactoui.git")
        }
    }

    // Ini akan mempublikasikan ke Sonatype (staging)
//    publishToMavenCentral()

    publishToMavenCentral()
    // Tandatangani semua publikasi
    signAllPublications()
}
