import com.archoid.modules.Modules
import com.archoid.utils.withProjects

plugins {
	`android-library`
	`kotlin-android`
}

apply<com.archoid.BuildPlugin>()

android {
	namespace = "com.archoid.data"
}

withProjects(
	Modules.Domain,
	Modules.DataApiLocal,
	Modules.DataApiRemote
)
