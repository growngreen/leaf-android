import AppDependencies.hilt

plugins {
    library()
}

dependencies {
    implementation(projects.modules.providers.network)
    implementation(projects.modules.providers.cache)
    implementation(projects.modules.domain.model)

    implementation(AppDependencies.kotlin)
    hilt()
    implementation(AppDependencies.coroutines)
    implementation(AppDependencies.timber)
}