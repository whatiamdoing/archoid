package com.archoid.launch.di

import com.archoid.core_ui.Screens
import com.archoid.core_ui.di.DaggerComponent
import com.archoid.core_ui.di.dependencies.ComponentDependencies
import com.archoid.core_ui.di.dependencies.DispatchersDependencies
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.domain.repository.AccountRepository
import com.archoid.launch.ui.LaunchFragment
import com.github.terrakok.cicerone.Router
import dagger.Component

interface LaunchDependencies: ComponentDependencies, DispatchersDependencies {
	fun router(): Router
	fun screens(): Screens
	fun accountRepository(): AccountRepository
}

@PerFeature
@Component(
	modules = [LaunchModule::class],
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