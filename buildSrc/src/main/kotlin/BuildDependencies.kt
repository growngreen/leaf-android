object BuildDependencies {
    val ktlint = NameVersion("org.jlleitschuh.gradle.ktlint", "11.0.0")
    val version = NameVersion("com.github.ben-manes.versions", "0.39.0")

    object Version {
        val ktlint = "0.43.0"
    }
}

data class NameVersion(val name: String, val version: String) {
    operator fun invoke() = "$name:$version"
}