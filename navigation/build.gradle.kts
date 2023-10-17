import com.archoid.dependencies.Dependencies
import com.archoid.utils.withProjects
import com.archoid.modules.Modules

plugins {
	`android-library`
	`kotlin-android`
}

apply<com.archoid.BuildPlugin>()

android {
	namespace = "com.archoid.navigation"
}

withProjects(
	Modules.Feature.Launch
)

dependencies {
	api(Dependencies.Navigation.cicerone)
	implementation(Dependencies.AndroidX.fragment)
}