import com.archoid.dependencies.Dependencies

plugins {
	`android-library`
	`kotlin-android`
}

apply<com.archoid.BuildPlugin>()

android {
	namespace = "com.archoid.resources"
}

dependencies {
	implementation(Dependencies.material)
}