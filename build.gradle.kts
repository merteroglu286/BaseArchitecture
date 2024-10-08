// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(ClassPlugins.build)
        classpath(ClassPlugins.kotlin)
        classpath(ClassPlugins.hilt)
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.google.gms:google-services:${Versions.googleService}")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.5")


    }
}


allprojects {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }

    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}