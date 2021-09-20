plugins {
    kotlin("multiplatform") version "1.5.21"
    id("com.android.library")
    id("kotlin-android-extensions")
    id("maven-publish")
    id("io.github.gradle-nexus.publish-plugin") version "1.0.0"
    signing
}

// auto publis
nexusPublishing {
    repositories {
        sonatype {  //only for users registered in Sonatype after 24 Feb 2021
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
//            nexusUrl.set(uri(project.properties["publishUrl"] as? String ?: "https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(project.properties["publishUser"] as? String ?: "")
            password.set(project.properties["publishPassword"] as? String ?: "")
        }
    }
}

group = "io.github.zotornit"
version = "1.0.2-SNAPSHOT"

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

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

publishing {
    repositories {
        maven {
            url = uri(project.properties["publishUrl"] as? String ?: "https://s01.oss.sonatype.org/content/repositories/snapshots/")
            credentials {
                username = project.properties["publishUser"] as? String ?: ""
                password = project.properties["publishPassword"] as? String ?: ""
            }
        }
    }


    // Configure all publications
    publications.withType<MavenPublication> {

        // Stub javadoc.jar artifact
        artifact(javadocJar.get())

        // Provide artifacts information requited by Maven Central
        pom {
            name.set("kmm-squirrel")
            description.set("Kotlin KMM Android/iOS library for simple key-value data storage")
            url.set("https://github.com/zotornit/kmm-squirrel")

            licenses {
                license {
                    name.set("The Apache Software License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            developers {
                developer {
                    id.set("ThomasPronold")
                    name.set("Thomas Pronold")
                    email.set("tp@zotorn.de")
                }
            }
            scm {
                url.set("https://github.com/zotornit/kmm-squirrel")
            }

        }
    }
}

// we us useGpgCmd() below, but on (my) build macOS, gpg is the gpg2 cmd
// I use what comes with `brew install gpg`
ext["signing.gnupg.executable"] = "gpg"

// last 8 digits
ext["signing.gnupg.keyName"] = (project.properties["signingKeyId"] as? String ?: "").takeLast(8)
ext["signing.gnupg.passphrase"] = project.properties["signingPassphrase"] as? String ?: ""

signing {
    useGpgCmd()
    sign(publishing.publications)
}