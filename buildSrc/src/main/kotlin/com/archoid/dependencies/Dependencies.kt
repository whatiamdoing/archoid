package com.archoid.dependencies

import com.archoid.Versions

object Dependencies {
	const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
	const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

	object Kotlin {
		const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
	}
}