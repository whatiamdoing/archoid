import com.archoid.modules.Modules
import com.archoid.utils.withApiProject

plugins {
	`android-library`
	`kotlin-android`
}

apply<com.archoid.BuildPlugin>()

android {
	namespace = "com.archoid.core_ui"
}

withApiProject(
	Modules.Navigation
)