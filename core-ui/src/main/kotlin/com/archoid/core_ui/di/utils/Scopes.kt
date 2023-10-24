package com.archoid.core_ui.di.utils

import javax.inject.Scope

@Scope
@Target(
	AnnotationTarget.CLASS,
	AnnotationTarget.FUNCTION
)
annotation class PerFeature

@Scope
@Target(
	AnnotationTarget.CLASS,
	AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.RUNTIME)
annotation class PerFlow