import AppDependencies.hilt

plugins {
    library()
}

dependencies {
    implementation(AppDependencies.timber)
    implementation(AppDependencies.lumberjack)
    hilt()
}