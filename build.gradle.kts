// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(BuildDependencies.ktlint.name) version BuildDependencies.ktlint.version
    id(BuildDependencies.version.name) version BuildDependencies.version.version
}
buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://jitpack.io")
    }
    dependencies {
//        classpath("com.google.gms:google-services:${AppDependencies.Version.googleServices}")
//        classpath("com.google.firebase:firebase-crashlytics-gradle:${AppDependencies.Version.firebaseCrashlytics}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }
}

subprojects {
    apply {
        plugin(BuildDependencies.ktlint.name)
        plugin(BuildDependencies.version.name)
    }

    repositories {
        google()
        mavenCentral()
    }

    ktlint {
        debug.set(false)
        version.set(BuildDependencies.Version.ktlint)
        verbose.set(false)
        android.set(true)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(false)
        // wildcard imports needed for compose for now
        disabledRules.set(listOf("no-wildcard-imports", "max-line-length", "import-ordering", "final-newline"))
        filter {
            exclude("**/generated/**")
            exclude("**/kotlin/**")
            exclude("**/java/**")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
