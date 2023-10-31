package com.archoid.global.di.scopes

import javax.inject.Scope

@Scope
@Target(
	AnnotationTarget.CLASS,
	AnnotationTarget.FUNCTION
)
annotation class AppScope