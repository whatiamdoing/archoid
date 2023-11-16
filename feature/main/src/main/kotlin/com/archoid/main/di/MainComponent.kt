package com.archoid.main.di

import com.archoid.core_ui.Screens
import com.archoid.core_ui.di.DaggerComponent
import com.archoid.core_ui.di.dependencies.ComponentDependencies
import com.archoid.core_ui.di.dependencies.DispatchersDependencies
import com.archoid.core_ui.di.utils.PerFeature
import com.archoid.domain.repository.AccountRepository
import com.archoid.main.ui.MainFragment
import com.github.terrakok.cicerone.Router
import dagger.Component

interface MainDependencies: ComponentDependencies, DispatchersDependencies {
	fun accountRepository(): AccountRepository
	fun router(): Router
	fun screens(): Screens
}

@PerFeature
@Component(
	modules = [MainModule::class],
	dependencies = [MainDependencies::class]
)
interface MainComponent: DaggerComponent {
	fun inject(fragment: MainFragment)

	@Component.Factory
	interface Factory {
		fun create(
			dependencies: MainDependencies
		): MainComponent
	}
}