pluginManagement {
	repositories {
		google()
		mavenCentral()
		gradlePluginPortal()
	}
}
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
	}
}

rootProject.name = "archoid"

include(":app")

include(":core-ui")

include(":feature:launch")
include(":feature:auth")

include(":domain")

include(":data")
include(":data-api-remote")
include(":data-api-local")

include(":global")