plugins {
    kotlin("multiplatform") version "1.5.21"
    id("com.android.library")
    id("kotlin-android-extensions")
}

group = "de.zotorn.kmm-squirrel"
version = "1.0.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

kotlin {
    android()
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
//                implementation("com.google.android.material:material:1.2.1")
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
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }
}