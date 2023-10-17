package com.archoid.app.di

import com.archoid.app.RootActivity
import com.archoid.navigation.di.DaggerNavigationComponent
import com.archoid.navigation.di.NavigationDependencies
import dagger.Component

@Component(
	dependencies = [NavigationDependencies::class]
)
interface AppComponent: NavigationDependencies {
	fun inject(activity: RootActivity)

	@Component.Factory
	interface Factory {
		fun create(
			navigationDependencies: NavigationDependencies
		): AppComponent
	}

	companion object {
		fun start(): AppComponent {
			val navigationDependencies = DaggerNavigationComponent.create()
			return DaggerAppComponent
				.factory()
				.create(
					navigationDependencies = navigationDependencies
				)
		}
	}
}