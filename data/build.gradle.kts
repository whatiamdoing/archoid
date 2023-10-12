plugins {
	`android-library`
	`kotlin-android`
}

apply<com.archoid.BuildPlugin>()

android {
	namespace = "com.archoid.data"
}

dependencies {
	implementation(project(":domain"))
	implementation(project(":data-api-local"))
	implementation(project(":data-api-remote"))
}
