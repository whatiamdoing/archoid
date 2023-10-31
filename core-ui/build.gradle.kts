import com.archoid.dependencies.Dependencies
import com.archoid.modules.Modules
import com.archoid.utils.withLibs
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

withLibs(
	Dependencies.AndroidX.fragment,
	Dependencies.Navigation.cicerone,
	Dependencies.material
)