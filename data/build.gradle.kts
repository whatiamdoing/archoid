import com.archoid.modules.Modules
import com.archoid.utils.withProjects
import com.archoid.dependencies.Dependencies
import com.archoid.utils.withLibs

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
	Modules.Global,
	Modules.DataApiLocal,
	Modules.DataApiRemote
)


withLibs(
	Dependencies.Kotlin.coroutines,
	Dependencies.gson
)