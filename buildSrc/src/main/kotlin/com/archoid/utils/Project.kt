package com.archoid.utils

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal val Project.libraryExtension: LibraryExtension
	get() = extensions.getByType()