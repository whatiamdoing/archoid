package com.archoid.dependencies

import com.archoid.Versions

object BuildDependencies {
	const val androidGradle = "com.android.tools.build:gradle:${Versions.gradle}"
	const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}