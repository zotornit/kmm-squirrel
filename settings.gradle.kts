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
        }
    }
}
rootProject.name = "kmm-squirrel"

