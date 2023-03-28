import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    object Version {
        // versions common to app dependencies, not referenced externally
        const val kotlin = "1.7.10" // also change in buildSrc build.gradle.kts file
        const val kotlinSerialization = "1.3.0-RC"
        const val google = "1.3.0"
        const val firebaseBom = "28.4.0"
        const val hiltVersion = "2.42"
        const val kotlinCoroutines = "1.5.1"
        const val compose = "1.3.0"
        const val composeActivity = "1.5.1"
        const val composeConstraint = "1.0.1"
        const val lifecycle = "2.4.0-alpha02"
        const val navigation = "2.4.0-alpha10"
        const val dataStorePreference = "1.0.0"
        const val firebaseCrashlytics = "2.7.1"
        const val googleServices = "4.3.10"
        const val leakCanary = "2.2"
        const val lottie = "5.2.0"
        const val timber = "4.7.1"
        const val splash = "1.0.0-beta02"
        const val splashScreen = "1.0.0"
        const val chromeTabs = "1.4.0"
        const val lumberJack = "5.2.0"
        const val lifecycleLog = "3.1.1"
        const val coil = "2.2.2"
    }

    // keep alphabetized
    const val androidxAnnotation = "androidx.annotation:annotation:1.2.0"
    const val appcompat = "androidx.appcompat:appcompat:${Version.google}"
    const val androidStartup = "androidx.startup:startup-runtime:1.1.1"
    const val fragment = "androidx.fragment:fragment-ktx:1.3.6"
    const val timber = "com.jakewharton.timber:timber:${Version.timber}"
    const val lumberjack = "com.github.MFlisar.Lumberjack:lumberjack-library:${Version.lumberJack}"
    const val splash = "androidx.core:core-splashscreen:${Version.splash}"
    const val lifecycleLog = "com.github.Chesire:LifecykleLog:${Version.lifecycleLog}"
    const val lottie = "com.airbnb.android:lottie-compose:${Version.lottie}"
    const val chromeTabs = "androidx.browser:browser:${Version.chromeTabs}"
    const val coil = "io.coil-kt:coil-compose:${Version.coil}"

    const val crypto = "com.google.crypto.tink:tink-android:1.7.0"

    const val splashScreen = "androidx.core:core-splashscreen:${Version.splashScreen}"

    const val composeBom = "androidx.compose:compose-bom:2022.10.00"
    const val composeRuntime = "androidx.compose.runtime:runtime:1.3.0"


    @Suppress("UnstableApiUsage")
    val compose = dependencies(
        "androidx.compose.animation:animation",
        "androidx.compose.foundation:foundation",
        "androidx.compose.material:material",
        "androidx.compose.material3:material3",
        "androidx.compose.runtime:runtime",
        "androidx.compose.ui:ui",
        "androidx.compose.ui:ui-util",
        "androidx.compose.ui:ui-tooling",
        "androidx.activity:activity-compose:${Version.composeActivity}",
        "androidx.constraintlayout:constraintlayout-compose:${Version.composeConstraint}"
    )

    const val coreKtx = "androidx.core:core-ktx:1.5.0"

    val coroutines = dependencies(
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinCoroutines}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.kotlinCoroutines}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Version.kotlinCoroutines}"
    )

    const val accompanistAnimation = "com.google.accompanist:accompanist-navigation-animation:0.20.0"

    const val dataStorePreferences = "androidx.datastore:datastore-preferences:${Version.dataStorePreference}"
    const val firebaseAnnotation = "com.google.firebase:firebase-bom:${Version.firebaseBom}"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Version.leakCanary}"

    val firebase = dependencies(
        "com.google.firebase:firebase-crashlytics-ktx",
        "com.google.firebase:firebase-analytics-ktx",
        "com.google.firebase:firebase-auth",
        "com.google.firebase:firebase-messaging",
        "com.google.firebase:firebase-firestore-ktx",
        "com.google.firebase:firebase-storage-ktx"
    )

    val retrofit = dependencies(
        "com.squareup.retrofit2:retrofit:2.9.0",
        "com.squareup.retrofit2:converter-gson:2.9.0",
        "com.squareup.okhttp3:okhttp:4.9.1",
        "com.squareup.okhttp3:logging-interceptor:4.9.1"
    )

    fun DependencyHandler.hilt() {
        add("implementation", "com.google.dagger:hilt-android:${Version.hiltVersion}")
        add("kapt", "com.google.dagger:hilt-compiler:${Version.hiltVersion}")
    }

    fun DependencyHandler.hiltCoroutinesAnnotation() {
        implementation(listOf(androidxAnnotation))
        api(coroutines)
        hilt()
    }

    fun DependencyHandler.dataStorage() {
        implementation(listOf("org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.kotlinSerialization}"))
        implementation(listOf("androidx.datastore:datastore-preferences:${Version.dataStorePreference}"))
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.kotlinSerialization}"

    const val material = "com.google.android.material:material:${Version.google}"

    private const val accompanistVersion = "0.24.11-rc"

    val accompanist = dependencies(
        "com.google.accompanist:accompanist-insets:$accompanistVersion",
        "com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion",
        "com.google.accompanist:accompanist-flowlayout:$accompanistVersion"
    )

    val composeNavigation = dependencies(
        "androidx.navigation:navigation-compose:${Version.navigation}",
        "com.google.accompanist:accompanist-navigation-material:$accompanistVersion",
        "com.google.accompanist:accompanist-navigation-animation:$accompanistVersion",
        "androidx.navigation:navigation-ui-ktx:${Version.navigation}",
        "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03",
        "androidx.compose.runtime:runtime-livedata:1.0.4"
    )

    val lifecycle = dependencies(
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}",
        "androidx.lifecycle:lifecycle-compiler:${Version.lifecycle}",
        "androidx.lifecycle:lifecycle-common-java8:${Version.lifecycle}",
        "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
    )

    object Test {
        private object Version {
            // versions common to app dependencies, not referenced externally
            const val androidXJUnit = "1.1.3"
            const val mockk = "1.12.0"
            const val kotest = "4.6.1"
            const val jUnit = "5.3.1"
        }

        // here define groups of dependencies and an associated extension function
        val testLibraries = dependencies(
            // keep alphabetized
            "androidx.arch.core:core-testing:2.1.0",
            "androidx.test.ext:junit:${Version.androidXJUnit}",
            "androidx.test.ext:junit-ktx:${Version.androidXJUnit}",
            "app.cash.turbine:turbine:0.6.0",
            "io.kotest:kotest-framework-datatest:${Version.kotest}",
            "io.kotest:kotest-runner-junit5:${Version.kotest}",
            "io.kotest:kotest-assertions-core:${Version.kotest}",
            "io.kotest:kotest-property:${Version.kotest}",
            "io.mockk:mockk:${Version.mockk}",
            "org.junit.jupiter:junit-jupiter-api:${Version.jUnit}",
            "org.jetbrains.kotlin:kotlin-reflect:${AppDependencies.Version.kotlin}",
        )
        val jUnitRuntime = "org.junit.jupiter:junit-jupiter-engine:${Version.jUnit}"
    }

    private fun dependencies(vararg dependency: String) =
        arrayListOf<String>().apply {
            addAll(dependency)
        }
}

fun DependencyHandler.implementPlatform(dependency: String) {
    add("implementation", platform(dependency))
}

fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.testRuntimeOnly(dependency: String) {
    add("testRuntimeOnly", dependency)
}

fun DependencyHandler.api(list: List<String>) {
    list.forEach { dependency ->
        add("api", dependency)
    }
}