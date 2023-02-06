
import AppDependencies.firebaseAnnotation
import AppDependencies.hiltCoroutinesAnnotation

plugins {
    app()
    compose()
}

dependencies {
    implementation(projects.modules.domain.model)
    implementation(projects.modules.presentation)
    implementation(projects.modules.common.ui)

    implementation(AppDependencies.appcompat)
    implementation(AppDependencies.androidStartup)
    implementation(AppDependencies.coreKtx)

    implementation(AppDependencies.lifecycleLog)
    implementation(AppDependencies.splash)

    hiltCoroutinesAnnotation()
    implementation(AppDependencies.lifecycle)
    implementation(AppDependencies.kotlin)
    implementation(AppDependencies.composeNavigation)

    implementPlatform(firebaseAnnotation)
    implementation(AppDependencies.firebase)

    implementation(AppDependencies.retrofit)

    implementation(AppDependencies.timber)
    implementation(AppDependencies.dataStorePreferences)

    testRuntimeOnly(AppDependencies.Test.jUnitRuntime)
}