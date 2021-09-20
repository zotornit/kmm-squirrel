plugins {
    kotlin("multiplatform") version "1.5.21"
    id("com.android.library")
    id("kotlin-android-extensions")
    id("maven-publish")
}

group = "de.zotorn.kmm-squirrel"
version = "1.0.7-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

kotlin {
    android {
        publishLibraryVariants("release", "debug")
        publishLibraryVariantsGroupedByFlavor = true
    }
    iosX64("ios") {
        binaries {
            framework {
                baseName = "KMMSquirrel"
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.security:security-crypto-ktx:1.1.0-alpha03") // requires Android SDK 23
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting
        val iosTest by getting
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(31)
    }
}

publishing {
    repositories {
        maven {
        }
    }
}