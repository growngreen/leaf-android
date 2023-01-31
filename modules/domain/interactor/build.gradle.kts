import AppDependencies.hilt

plugins {
    library()
}

dependencies {
    implementation(projects.modules.domain.model)
    implementation(projects.modules.domain.repository)

    implementation(AppDependencies.kotlin)
    hilt()
    implementation(AppDependencies.coroutines)
    implementation(AppDependencies.timber)
}