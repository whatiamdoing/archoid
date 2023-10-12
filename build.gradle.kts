buildscript {

	repositories {
		google()
		mavenCentral()
	}

	dependencies {
		classpath(com.archoid.dependencies.BuildDependencies.androidGradle)
		classpath(com.archoid.dependencies.BuildDependencies.kotlinGradlePlugin)
	}

}

plugins {
	id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
}