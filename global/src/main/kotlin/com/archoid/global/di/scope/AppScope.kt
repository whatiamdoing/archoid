package com.archoid.global.di.scope

import javax.inject.Scope

@Scope
@Target(
	AnnotationTarget.CLASS,
	AnnotationTarget.FUNCTION
)
annotation class AppScope