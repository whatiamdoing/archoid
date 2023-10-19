package com.archoid.app.di

import com.archoid.app.RootActivity
import com.archoid.app.di.dependencies.FlowsDependencies
import com.archoid.app.di.dependencies.FlowsDependenciesModule
import com.archoid.app.di.navigation.module.NavigationBindsModule
import com.archoid.app.di.navigation.module.NavigationModule
import dagger.Component

@AppScope
@Component(
	modules = [
		NavigationModule::class,
		NavigationBindsModule::class,
		FlowsDependenciesModule::class
	]
)
interface AppComponent: FlowsDependencies {
	fun inject(activity: RootActivity)

	@Component.Factory
	interface Factory {
		fun create(): AppComponent
	}

	companion object {
		fun start(): AppComponent {
			return DaggerAppComponent
				.factory()
				.create()
		}
	}
}