/**
 * configuration of the android app parameters
 */
object AppConfig {
    const val compileSdk = 33
    const val minSdk = 26
    const val targetSdk = 33

    /*
    Notes about these next two vals...

    Q - "Why are they split on two lines weirdly like that?"
    A - We're using a bitrise workflow step to replace these values with "real" ones during
    the bitrise CI build. By splitting these declarations in this way, the default scripts can
    find and edit the values.  IF they're on one line, it doesn't work and we'd need to write a
    custom step. This awkward formatting is preferred.

    Q - "Do I ever have to change these values?"
    A - Not technically. The source of truth for "real" builds is bitrise, so these values are only
    ever in play for manual builds done by a developer.  What this means, though, is that if you
    ever do a one-off build for someone, the build numbers will always be the same.  You shouldn't
    do a one-off for someone this way, though.  Use bitrise.
     */

    const val versionCode = 1
    const val versionName = "0.1"


    const val appId = "com.github.sgeorgiev24.leaf"
    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val dimension = "environment"

    const val outputFilenamePrefix = "Leaf"

    enum class Flavor(
        val flavorName: String,
        val dimension: String = AppConfig.dimension,
        val hasSourceSet: Boolean = true,
        val appSuffix: String? = null,
        val baseUrl: String
    ) {
        Prod("prod", hasSourceSet = false, baseUrl = ""),
        Staging("staging", appSuffix = ".staging", baseUrl = ""),
        // Use this build with different url when you need to test something or run locally
        Dev("dev", appSuffix = ".dev", baseUrl = ""),
    }
}