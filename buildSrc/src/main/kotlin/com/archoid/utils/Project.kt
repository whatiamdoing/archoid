package com.archoid.utils

import com.android.build.gradle.LibraryExtension
import com.archoid.modules.base.Module
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal val Project.libraryExtension: LibraryExtension
	get() = extensions.getByType()

fun Project.withLibs(
	vararg libs: String
) {
	dependencies {
		libs.forEach { dep ->
			implementation(dep)
		}
	}
}

fun Project.withApiProject(vararg modules: Module) {
	dependencies {
		modules.forEach { module ->
			api(
				project(
					module.toString()
				)
			)
		}
	}
}

fun Project.withProjects(vararg modules: Module) {
	dependencies {
		modules.forEach { module ->
			implementation(
				project(module.toString())
			)
		}
	}
}