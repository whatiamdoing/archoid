package com.archoid.app.di.dependencies

import com.archoid.app.di.AppComponent
import com.archoid.app.di.AppScope
import com.archoid.core_ui.di.dependencies.ComponentDependencies
import com.archoid.core_ui.di.dependencies.ComponentDependencyKey
import com.archoid.launch.di.LaunchDependencies
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FlowsDependenciesModule {

	@AppScope
	@Binds
	@IntoMap
	@ComponentDependencyKey(LaunchDependencies::class)
	fun launchDependencies(dependencies: AppComponent): ComponentDependencies

}