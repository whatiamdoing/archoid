import com.archoid.dependencies.Dependencies

plugins {
	`android-library`
	`kotlin-android`
}

apply<com.archoid.BuildPlugin>()

android {
	namespace = "com.archoid.core_ui"
}

dependencies {
	implementation(Dependencies.Navigation.cicerone)
}