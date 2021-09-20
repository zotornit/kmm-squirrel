pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
                // version should kinda match gradle properties version, current values at :https://developer.android.com/studio/releases/gradle-plugin
                useModule("com.android.tools.build:gradle:7.0.2")
            }
//            if(requested.id.name == "io.codearte.nexus-staging") {
//                useModule("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:0.30.0")
//            }
        }
    }
}
rootProject.name = "kmm-squirrel"

