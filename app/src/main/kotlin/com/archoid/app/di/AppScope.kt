package com.archoid.app.di

import javax.inject.Scope

@Scope
@Target(
	AnnotationTarget.CLASS,
	AnnotationTarget.FUNCTION
)
internal annotation class AppScope