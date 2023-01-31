import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.gradle.kotlin.dsl.withType
import org.gradle.plugin.use.PluginDependenciesSpec

fun PluginDependenciesSpec.app() {
    id("com.android.application")
//    id("com.google.gms.google-services")
//    id("com.google.firebase.crashlytics")
    common()
}

fun PluginDependenciesSpec.library() {
    id("com.android.library")
    common()
}

fun PluginDependenciesSpec.librarySimple() {
    id("com.android.library")
    id("AndroidSetupPlugin")
}

fun PluginDependenciesSpec.compose() {
    id("AddComposePlugin")
}

private fun PluginDependenciesSpec.common() {
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("AndroidSetupPlugin")
    id("kotlin-parcelize")
    kotlin("plugin.serialization") version AppDependencies.Version.kotlin
}


fun Project.setupKotlinModule() {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

    tasks.withType<JavaCompile> {
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    }
}
