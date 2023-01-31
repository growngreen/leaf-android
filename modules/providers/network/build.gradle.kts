
import AppDependencies.hilt

plugins {
    library()
}

dependencies {
    hilt()
    implementation(AppDependencies.coroutines)
    implementation(AppDependencies.retrofit)
    implementation(AppDependencies.kotlinSerialization)

    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

//    implementPlatform(firebaseAnnotation)
//    implementation(AppDependencies.firebase)
}