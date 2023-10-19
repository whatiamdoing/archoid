package com.archoid.launch.di

import com.archoid.core_ui.di.DaggerComponent
import com.archoid.core_ui.di.dependencies.ComponentDependencies
import com.archoid.core_ui.di.utils.PerFragment
import com.archoid.launch.ui.LaunchFragment
import com.github.terrakok.cicerone.Router
import dagger.Component

interface LaunchDependencies: ComponentDependencies {
	fun router(): Router
}

@PerFragment
@Component(
	dependencies = [LaunchDependencies::class]
)
internal interface LaunchComponent : DaggerComponent {

	@Component.Factory
	interface Factory {
		fun create(
			dependencies: LaunchDependencies
		): LaunchComponent
	}

	fun inject(fragment: LaunchFragment)
}