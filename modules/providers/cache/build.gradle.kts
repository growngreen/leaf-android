import AppDependencies.hilt

plugins {
    library()
}

dependencies {
    implementation(projects.modules.common.base)
    implementation(AppDependencies.kotlin)
    hilt()
    implementation(AppDependencies.coroutines)
}