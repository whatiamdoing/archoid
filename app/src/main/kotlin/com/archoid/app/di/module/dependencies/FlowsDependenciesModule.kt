package com.archoid.app.di.module.dependencies

import com.archoid.app.di.AppComponent
import com.archoid.app.di.AppScope
import com.archoid.auth.AuthDependencies
import com.archoid.core_ui.di.dependencies.ComponentDependencies
import com.archoid.core_ui.di.dependencies.ComponentDependencyKey
import com.archoid.launch.di.LaunchDependencies
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

interface FlowsDependencies:
	LaunchDependencies,
	AuthDependencies

@Module
interface FlowsDependenciesModule {

	@AppScope
	@Binds
	@IntoMap
	@ComponentDependencyKey(LaunchDependencies::class)
	fun launchDependencies(component: AppComponent): ComponentDependencies

	@AppScope
	@Binds
	@IntoMap
	@ComponentDependencyKey(AuthDependencies::class)
	fun authDependencies(component: AppComponent): ComponentDependencies

}