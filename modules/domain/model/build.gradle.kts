import AppDependencies.hilt

plugins {
    library()
}

dependencies {
    implementation(AppDependencies.kotlin)
    implementation(AppDependencies.composeRuntime)
    hilt()
    implementation(AppDependencies.coroutines)
    implementation(AppDependencies.timber)
}