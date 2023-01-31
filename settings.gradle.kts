rootProject.name = ("Leaf")
include(
    "app",
    "modules:common:ui",
    "modules:presentation",
    "modules:domain:repository",
    "modules:domain:interactor",
    "modules:domain:model",
    "modules:providers:network",
    "modules:providers:cache"
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
