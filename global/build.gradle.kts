import com.archoid.utils.withLibs
import com.archoid.dependencies.Dependencies

plugins {
	`android-library`
	`kotlin-android`
}

apply<com.archoid.BuildPlugin>()

android {
	namespace = "com.archoid.global"
}

withLibs(
	Dependencies.Kotlin.coroutines
)