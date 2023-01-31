import AppDependencies.hilt

plugins {
    library()
    compose()
}

dependencies {
    implementation(projects.modules.domain.model)

    implementation(AppDependencies.kotlin)
    hilt()
    implementation(AppDependencies.coroutines)
    implementation(AppDependencies.accompanist)
}