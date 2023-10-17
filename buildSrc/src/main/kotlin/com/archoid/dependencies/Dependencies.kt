package com.archoid.dependencies

internal object Versions {
	const val dagger = "2.48.1"
	const val kotlinCoroutines = "1.7.3"
	const val cicerone = "7.1"
	const val fragment = "1.6.1"
	const val viewBindingDelegate = "1.5.9"

	const val gradle = "8.1.1"
	const val kotlin = "1.9.0"
}

object Dependencies {
	const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
	const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
	const val viewBindingDelegate = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBindingDelegate}"

	object Navigation {
		const val cicerone = "com.github.terrakok:cicerone:${Versions.cicerone}"
	}

	object AndroidX {
		const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
	}

	object Kotlin {
		const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
	}
}