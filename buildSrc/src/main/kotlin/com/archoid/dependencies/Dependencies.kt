package com.archoid.dependencies

internal object Versions {
	const val dagger = "2.48.1"
	const val kotlinCoroutines = "1.7.3"
	const val cicerone = "7.1"
	const val fragment = "1.6.1"
	const val datastore = "1.0.0"
	const val viewBindingDelegate = "1.5.9"
	const val material = "1.10.0"
	const val gson = "2.10.1"

	const val inject = "1"
	const val gradle = "8.1.1"
	const val kotlin = "1.9.0"

	const val junit5 = "5.7.2"
}

object Dependencies {
	const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
	const val inject = "javax.inject:javax.inject:${Versions.inject}"
	const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
	const val material = "com.google.android.material:material:${Versions.material}"
	const val gson = "com.google.code.gson:gson:${Versions.gson}"
	const val junit = "org.junit.jupiter:junit-jupiter:${Versions.junit5}"

	const val viewBindingDelegate = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.viewBindingDelegate}"

	object Navigation {
		const val cicerone = "com.github.terrakok:cicerone:${Versions.cicerone}"
	}

	object AndroidX {
		const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
		const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"
	}

	object Kotlin {
		const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
	}
}