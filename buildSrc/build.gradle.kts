plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

// Note: this can't reference Versions.kt in buildSrc so the versions is repeated here
object BuildVersion {
    const val kotlin = "1.6.21"
    const val hiltVersion = "2.42"
}

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:7.2.2")
    implementation(kotlin("gradle-plugin", "1.7.10"))
    implementation(kotlin("android-extensions"))

    implementation("com.google.dagger:hilt-android-gradle-plugin:${BuildVersion.hiltVersion}")
}

gradlePlugin {
    plugins {
        register("AndroidSetupPlugin") {
            id = "AndroidSetupPlugin"
            implementationClass = "AndroidSetupPlugin"
        }
        register("AddComposePlugin") {
            id = "AddComposePlugin"
            implementationClass = "AddComposePlugin"
        }
    }
}