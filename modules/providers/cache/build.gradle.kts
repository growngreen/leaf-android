import AppDependencies.hilt

plugins {
    library()
}

dependencies {
    implementation(AppDependencies.kotlin)
    hilt()
    implementation(AppDependencies.coroutines)
}