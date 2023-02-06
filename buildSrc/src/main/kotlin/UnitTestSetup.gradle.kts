tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs = mutableListOf("--enable-preview")
}