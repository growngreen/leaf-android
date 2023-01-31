@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AddComposePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.addCompose()
    }

    private fun Project.addCompose() {
        androidExtension?.addCompose()
        dependencies {
            implementPlatform(AppDependencies.composeBom)
            implementation(AppDependencies.compose)
        }
    }

    private fun BaseExtension.addCompose() {
        buildFeatures.compose = true // this makes everything need the compose dependency

        composeOptions {
            kotlinCompilerExtensionVersion = AppDependencies.Version.compose
        }
    }
}