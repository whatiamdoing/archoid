package com.archoid.app.di

import com.archoid.app.RootActivity
import com.archoid.app.di.module.dependencies.FlowsDependencies
import com.archoid.app.di.module.dependencies.FlowsDependenciesModule
import com.archoid.app.di.module.navigation.NavigationBindsModule
import com.archoid.app.di.module.navigation.NavigationModule
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