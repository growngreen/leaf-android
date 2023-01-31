import AppDependencies.hilt

plugins {
    library()
    compose()
}

dependencies {
    implementation(projects.modules.domain.interactor)
    implementation(projects.modules.common.ui)
    implementation(projects.modules.domain.model)

    implementation(AppDependencies.lottie)
    implementation(AppDependencies.kotlin)
    implementation(AppDependencies.chromeTabs)
    hilt()
    implementation(AppDependencies.coroutines)
    implementation(AppDependencies.timber)
    implementation(AppDependencies.composeNavigation)
    implementation(AppDependencies.accompanist)
}