@file:Suppress("UnstableApiUsage") // todo revisit then when getting gradle upgrades from 7.1.1

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePluginExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

val commonCompilerArgs = listOfNotNull(
    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
    "-opt-in=kotlin.Experimental",
    "-opt-in=com.google.accompanist.pager.ExperimentalPagerApi",
    "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
    "-opt-in=androidx.compose.ui.unit.ExperimentalUnitApi",
    "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
    "-opt-in=kotlin.time.ExperimentalTime",
    "-opt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
    "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi"
)

/**
 * common setup for android modules. It should take care of the whole
 * android configuration block for libraries and part of it for app
 */
class AndroidSetupPlugin : Plugin<Project> {
    override fun apply(project: Project) {

        // any common plugins
        project.plugins.apply("kotlin-android")
        project.plugins.apply("UnitTestSetup")

        project.androidExtension?.let { androidExtension ->
            setupCommon(project, androidExtension)

            when (androidExtension) {
                is LibraryExtension -> setLibrary(androidExtension)
                is AppExtension -> setApp(project, androidExtension)
            }
        }
    }

    private fun setupCommon(project: Project, extension: BaseExtension) {
        val buildNumber: String? by project
        val buildName: String? by project
        // add all the test libraries to all projects
        project.dependencies {
            implementation(AppDependencies.Test.testLibraries)
//            add("testImplementation", project(":modules:common:test"))
        }

        extension.apply {
            compileSdkVersion(AppConfig.compileSdk)

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            defaultConfig {
                minSdk = AppConfig.minSdk
                targetSdk = AppConfig.targetSdk
                versionCode = getVerCode(project, buildNumber)
                versionName = getVerName(project, buildName)
                testInstrumentationRunner = AppConfig.androidTestInstrumentation
            }

            kotlinOptions {
                jvmTarget = "11"
                freeCompilerArgs = commonCompilerArgs
            }

            testOptions {
                unitTests.all {
                    it.useJUnitPlatform()
                }
            }

            lintOptions {
                isWarningsAsErrors = true
                isAbortOnError = false
            }

            packagingOptions {
                resources.excludes.add("META-INF/*.md")
            }
        }
    }

    private fun setLibrary(extension: LibraryExtension) {
        extension.apply {
            buildTypes {
                getByName("debug") {
                    isMinifyEnabled = false
                    isTestCoverageEnabled = true
                }
                getByName("release") {
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            flavorDimensions.add(AppConfig.dimension)

            productFlavors {
                AppConfig.Flavor.values().forEach {
                    create(it.flavorName) {
                        dimension = it.dimension
                    }
                }
            }

            sourceSets {
                named("test") {
                    java.srcDir("src/sharedTest/java")
                }
            }
        }
    }

    private fun setApp(project: Project, extension: AppExtension) {
        val baseUrlDebug: String by project
        val baseUrlStaging: String by project
        val baseUrlRelease: String by project

        extension.apply {
            defaultConfig {
                applicationId = AppConfig.appId
            }

            sourceSets {
                AppConfig.Flavor.values().filter {
                    it.hasSourceSet
                }.forEach {
                    create(it.flavorName) {
                        resources.srcDir("src/${it.flavorName}/res")
                    }

                }
            }

            flavorDimensionList.add(AppConfig.dimension)

            buildTypes {
                getByName("debug") {
                    isMinifyEnabled = false
                    isTestCoverageEnabled = true
                }
                getByName("release") {
                    isMinifyEnabled = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            productFlavors {
                AppConfig.Flavor.values().forEach {
                    create(it.flavorName) {
                        it.appSuffix?.let { appSuffix ->
                            applicationIdSuffix = appSuffix
                        }
                        dimension = it.dimension

                        when (it.flavorName) {
                            AppConfig.Flavor.Dev.flavorName -> {
                                buildConfigField("String", "BASE_URL", baseUrlDebug)
                                addManifestPlaceholders(mapOf("appLabel" to "Dev - Leaf"))
                            }
                            AppConfig.Flavor.Staging.flavorName -> {
                                buildConfigField("String", "BASE_URL", baseUrlStaging)
                                addManifestPlaceholders(mapOf("appLabel" to "Staging - Leaf"))
                            }
                            else -> {
                                buildConfigField("String", "BASE_URL", baseUrlRelease)
                                addManifestPlaceholders(mapOf("appLabel" to "Leaf"))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getVerCode(project: Project, buildNumber: String?): Int {
        return if (project.hasProperty("buildNumber")) {
            buildNumber?.toInt() ?: AppConfig.versionCode
        } else AppConfig.versionCode
    }

    private fun getVerName(project: Project, buildName: String?): String {
        return if (project.hasProperty("buildName")) {
            buildName ?: AppConfig.versionName
        } else AppConfig.versionName
    }
}

private fun BaseExtension.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

val Project.basePluginExtension
    get() = project.extensions.getByName("base") as? BasePluginExtension

val Project.androidExtension
    get() = extensions.getByName("android") as? BaseExtension