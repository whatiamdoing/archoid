import com.archoid.utils.withLibs
import com.archoid.dependencies.Dependencies
import com.archoid.modules.Modules
import com.archoid.utils.withProjects

plugins {
	`android-library`
	`kotlin-android`
}

apply<com.archoid.BuildPlugin>()

android {
	namespace = "com.archoid.data_api_local"
}

withProjects(
	Modules.Global
)

withLibs(
	Dependencies.gson,
	Dependencies.AndroidX.datastore
)