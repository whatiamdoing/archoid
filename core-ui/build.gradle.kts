import com.archoid.dependencies.Dependencies
import com.archoid.modules.Modules
import com.archoid.utils.withProjects

plugins {
	`android-library`
	`kotlin-android`
}

apply<com.archoid.BuildPlugin>()

android {
	namespace = "com.archoid.core_ui"
}

withProjects(
	Modules.Global
)

dependencies {
	implementation(Dependencies.AndroidX.fragment)
	implementation(Dependencies.Navigation.cicerone)
}